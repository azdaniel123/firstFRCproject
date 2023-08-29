package frc.trigon.robot.subsystems.turret;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.ControlModeValue;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

import java.lang.module.Configuration;

public class TurretConstants {

    private static final int MOTOR_ID = 0;

    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;

    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    private static final double
            P = 1,
            I = 0.2,
            D = 6;

    static final PIDController PID_CONTROLLER = new PIDController(P, I, D);

    static final TalonFX motor = new TalonFX(MOTOR_ID);

    static {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Audio.BeepOnBoot = true;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;

        PID_CONTROLLER.setSetpoint(0);
        motor.getConfigurator().apply(config);
    }
}
