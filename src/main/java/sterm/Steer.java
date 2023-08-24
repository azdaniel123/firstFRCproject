package sterm;


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

    public CommandBase getSetTargetAngleCOM(){
        return new SequentialCommandGroup(
                getSetTargetAngle(90).withTimeout(3),
                getSetTargetAngle(180).withTimeout(3),
                getSetTargetAngle(0)
        );
    }

    public CommandBase supplierToAngleValue(Supplier<Double> angle){
        return new FunctionalCommand(
                () -> {},
                () -> setTargetAngle(angle.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

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