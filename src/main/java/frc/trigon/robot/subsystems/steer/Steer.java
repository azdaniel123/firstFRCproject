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
     * @return a command was to make the steer align to 90 degrees, wait 3 seconds, align to 180 degrees, wait 3 seconds again, align to 0 degrees, and stop the motor.
     */
    public CommandBase getSetTargetAngleCOM(){
        return new SequentialCommandGroup(
                getSetTargetAngle(90).withTimeout(3),
                getSetTargetAngle(180).withTimeout(3),
                getSetTargetAngle(0)
        );
    }

    /**
     * make a command was get a Supplier<Double> and align to the value of the Supplier<Double>.
     * @param angleSupplier is a Supplier<Double> the command aligns with his value.
     * @return  a command was to get a Supplier<Double> and align to the value of the Supplier<Double>.
     */
    public CommandBase supplierToAngleValue(Supplier<Double> angleSupplier){
        return new FunctionalCommand(
                () -> {},
                () -> setTargetAngle(angleSupplier.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    /**
     * make a Command get an angle and align to that angle.
     * @param angle is a double of the angle we need to double to her.
     * @return a Command was get an angle, align to that angle and stop the motor.
     */
    public CommandBase getSetTargetAngle(double angle){
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