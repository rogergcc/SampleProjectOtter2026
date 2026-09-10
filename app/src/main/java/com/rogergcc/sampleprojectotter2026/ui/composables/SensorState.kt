package com.rogergcc.sampleprojectotter2026.ui.composables

import android.content.Context
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.launch

// 6. Sensor GIROSCÓPICO / VECTOR DE ROTACIÓN
data class SensorState(val pitch: Float = 0f, val roll: Float = 0f)

// ==========================================
// 4. SENSOR HOOK Y CONFIGURACIONES DE PRUEBA
// ==========================================

@Composable
fun rememberTicketSensors(): State<SensorState> {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val rawSensorState = remember { mutableStateOf(SensorState()) }

    DisposableEffect(lifecycleOwner) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event ?: return
                if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
                    val rotationMatrix = FloatArray(9)
                    val orientationAngles = FloatArray(3)
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
                    SensorManager.getOrientation(rotationMatrix, orientationAngles)

                    val pitchDeg = Math.toDegrees(orientationAngles[1].toDouble()).toFloat()
                    val rollDeg = Math.toDegrees(orientationAngles[2].toDouble()).toFloat()

                    rawSensorState.value = SensorState(
                        pitch = pitchDeg.coerceIn(-45f, 45f),
                        roll = rollDeg.coerceIn(-45f, 45f)
                    )
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                rotationSensor?.let {
                    sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_GAME)
                }
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                sensorManager.unregisterListener(listener)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            sensorManager.unregisterListener(listener)
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val animatedPitch by animateFloatAsState(
        targetValue = rawSensorState.value.pitch,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "pitchAnimation"
    )
    val animatedRoll by animateFloatAsState(
        targetValue = rawSensorState.value.roll,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "rollAnimation"
    )

    return remember(animatedPitch, animatedRoll) {
        derivedStateOf { SensorState(pitch = animatedPitch, roll = animatedRoll) }
    }
}

@Composable
fun rememberSimulatedOrRealSensors(
    isAutoAnimate: Boolean,
    realPitch: Float,
    realRoll: Float
): State<Pair<Float, Float>> {
    val infiniteTransition = rememberInfiniteTransition(label = "GyroSimulation")

    // Inclinación simulada de -12° a +12°
    val simulatedPitch by infiniteTransition.animateFloat(
        initialValue = -12f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "SimulatedPitch"
    )

    val simulatedRoll by infiniteTransition.animateFloat(
        initialValue = -8f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "SimulatedRoll"
    )

    return remember(isAutoAnimate, realPitch, realRoll, simulatedPitch, simulatedRoll) {
        derivedStateOf {
            if (isAutoAnimate) {
                simulatedPitch to simulatedRoll
            } else {
                realPitch to realRoll
            }
        }
    }
}

@Composable
fun TicketPreviewCaptureBox(
    onBitmapExported: (Bitmap) -> Unit,
    content: @Composable (GraphicsLayer) -> Unit
) {
    val graphicsLayer = rememberGraphicsLayer()
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.wrapContentSize()) {
        content(graphicsLayer)

        // Botón flotante rápido para exportar la captura
        IconButton(
            onClick = {
                scope.launch {
                    val bitmap = graphicsLayer.toImageBitmap().asAndroidBitmap()
                    onBitmapExported(bitmap)
                }
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .background(Color.Black.copy(alpha = 0.6f), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Exportar Vista Previa",
                tint = Color.White
            )
        }
    }
}

@Composable
fun TicketPreviewCaptureBox(
    onBitmapExported: (Bitmap) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (GraphicsLayer) -> Unit
) {
    val graphicsLayer = rememberGraphicsLayer()
    val scope = rememberCoroutineScope()

    Box(modifier = modifier) {
        content(graphicsLayer)

        // Botón flotante para generar y guardar el borrador
        FloatingActionButton(
            onClick = {
                scope.launch {
                    // Renderiza la capa visual exacta a un Bitmap
                    val bitmap = graphicsLayer.toImageBitmap().asAndroidBitmap()
                    onBitmapExported(bitmap)
                }
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp),
            containerColor = Color.Black.copy(alpha = 0.7f),
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Exportar Captura Borrador"
            )
        }
    }
}