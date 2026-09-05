package com.rogergcc.sampleprojectotter2026.ui.composables

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

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