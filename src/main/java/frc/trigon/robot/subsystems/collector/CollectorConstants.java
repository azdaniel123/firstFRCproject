package frc.trigon.robot.subsystems.collector;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class CollectorConstants {
    private static final int MOTOR_ID = 0;
    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);

    static final double
            COLLECT_VOLTAGE = 6,

            EJECT_VOLTAGE = 6;

    static {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.Audio.BeepOnBoot = true;
        configuration.MotorOutput.Inverted = INVERTED_VALUE;
        configuration.MotorOutput.NeutralMode =  NEUTRAL_MODE_VALUE;

        MOTOR.getConfigurator().apply(configuration);
    }
}
