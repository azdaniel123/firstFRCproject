package frc.trigon.robot.subsystems.steer;


import com.ctre.phoenixpro.controls.PositionVoltage;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.google.common.base.Supplier;
import edu.wpi.first.wpilibj2.command.*;

import javax.swing.text.Position;

public class Steer extends SubsystemBase {

    private final static Steer INSTANCE = new Steer();

    private final TalonFX MOTOR = SteerConstants.MOTOR;

    public static Steer getInstance() {
        return INSTANCE;
    }

    private Steer() {
    }

    /**
     * @return a command that align to 90 degrees, wait 3 seconds, align to 180 degrees, wait another 3 seconds, align to 0 degrees, and stop the motor.
     */
    public CommandBase getSetTargetAngleSequentialCommand() {
        return new SequentialCommandGroup(
                getSetTargetAngle(90).withTimeout(3),
                getSetTargetAngle(180).withTimeout(3),
                getSetTargetAngle(0)
        );
    }

    /**
     * Creates a command that gets a supplier and set the target Angle to this value.
     *
     * @param angleSupplier is a supplier of the target Angle
     * @return the command
     */
    public CommandBase getSetTargetAngleCommand(Supplier<Double> angleSupplier) {
        return new FunctionalCommand(
                () -> {
                },
                () -> setTargetAngle(angleSupplier.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    /**
     * Creates a command that gets an angle and set the target angle.
     *
     * @param angle the target angle
     * @return the command
     */
    public CommandBase getSetTargetAngle(double angle) {
        return startEnd(
                () -> setTargetAngle(angle),
                MOTOR::stopMotor
        );
    }

    private void setTargetAngle(double getAngle) {
        double systemRevolutions = getAngle / 360;
        double motorRevolutions = systemRevolutions * SteerConstants.GEAR_RATIO;
        PositionVoltage request = new PositionVoltage(motorRevolutions);
        MOTOR.setControl(request);
    }


    private void stop() {
        MOTOR.stopMotor();
    }
}