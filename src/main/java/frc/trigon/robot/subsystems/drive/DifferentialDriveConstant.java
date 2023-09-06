package frc.trigon.robot.subsystems.drive;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;

public class DifferentialDriveConstant {

    private static final int MOTOR_ID_LEFT_FORE = 0;
    private static final int MOTOR_ID_RIGHT_FORE = 1;
    private static final int MOTOR_ID_LEFT_REAR = 2;
    private static final int MOTOR_ID_RIGHT_REAR = 3;

    private static final InvertedValue INVERTED_VALUE = InvertedValue.Clockwise_Positive;

    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;


    static final TalonFX LEFT_FORE_MOTOR = new TalonFX(MOTOR_ID_LEFT_FORE);
    static final TalonFX RIGHT_FORE_MOTOR = new TalonFX(MOTOR_ID_RIGHT_FORE);
    static final TalonFX LEFT_REAR_MOTOR = new TalonFX(MOTOR_ID_LEFT_REAR);
    static final TalonFX RIGHT_REAR_MOTOR = new TalonFX(MOTOR_ID_LEFT_REAR);

    static {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Audio.BeepOnBoot = false;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;

        LEFT_FORE_MOTOR.getConfigurator().apply(config);
        RIGHT_FORE_MOTOR.getConfigurator().apply(config);
        LEFT_REAR_MOTOR.getConfigurator().apply(config);
        RIGHT_REAR_MOTOR.getConfigurator().apply(config);
    }

}
