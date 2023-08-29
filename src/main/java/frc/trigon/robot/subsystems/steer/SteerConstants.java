package frc.trigon.robot.subsystems.steer;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;


public class SteerConstants {

    static final double GEAR_RATIO = 12.8;
    private static final int MOTOR_ID = 0;

    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;

    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    private static final double
            P = 8.4,
            I = 0,
            D = 0;

    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);


    static {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Audio.BeepOnBoot = true;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        config.Slot0.kP = P;
        config.Slot0.kI = I;
        config.Slot0.kD = D;


        MOTOR.getConfigurator().apply(config);
    }

}
