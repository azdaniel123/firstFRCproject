package frc.trigon.robot.subsystems.turret;

import com.ctre.phoenixpro.controls.PositionVoltage;
import com.ctre.phoenixpro.controls.VoltageOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import javax.swing.text.Position;
import java.beans.Encoder;
import java.util.function.Supplier;

public class Turret extends SubsystemBase {

    private final static Turret INSTANCE = new Turret();

    public static Turret getInstance() {
        return INSTANCE;
    }

    private final TalonFX MOTOR = TurretConstants.motor;
    private final PIDController pidController = TurretConstants.PID_CONTROLLER;

    private Turret() {
    }

    /**
     * Creates a command that checks if there's a visible target, if there isn't- send the voltage request. if there is- calculate the PID to align the target.
     * @param currentPositionSupplier the Supplier of Position of the reflector
     * @param hasTargetSupplier if the target is visible
     * @return the command.
     */
    public CommandBase getAlignToReflectorCommend(Supplier<Double> currentPositionSupplier, Supplier<Boolean> hasTargetSupplier) {
        return new FunctionalCommand(
                () -> {
                },
                () -> alignToReflector(currentPositionSupplier.get(), hasTargetSupplier.get()),
                (interrupted) -> stop(),
                () -> false,
                this

        );
    }


    private void alignToReflector(double currentPosition, boolean hasTarget) {
        if (!hasTarget) {
            VoltageOut request = new VoltageOut(5);
            MOTOR.setControl(request);
            return;
        }

        VoltageOut request = new VoltageOut(pidController.calculate(currentPosition));
        MOTOR.setControl(request);
    }

    private void stop() {
        MOTOR.stopMotor();
    }
}