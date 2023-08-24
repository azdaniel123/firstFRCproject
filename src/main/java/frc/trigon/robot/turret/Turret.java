package frc.trigon.robot.turret;


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

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.

    private final static Turret INSTANCE = new Turret();

    public static Turret getInstance() {
        return INSTANCE;
    }

    private final TalonFX MOTOR = TurretConstants.motor;
    private final PIDController pidController = TurretConstants.PID_CONTROLLER;

    private Turret() {
    }

    /**
     * make the turret Tune in to the hub.
     * @param carrotPoseidonSupplier
     * @param hasTargetSupplier
     * @return
     */
    public CommandBase getAlainToReflectorCommend(Supplier<Double> carrotPoseidonSupplier, Supplier<Boolean> hasTargetSupplier){
        return new FunctionalCommand(
                () -> {},
                () -> alainToReflector(carrotPoseidonSupplier.get(), hasTargetSupplier.get() ),
                (interrupted) -> stop(),
                () -> false,
                this

        );
    }


    private void alainToReflector(double carrotPoseidon, boolean hasTarget){
        if (!hasTarget){
            VoltageOut request = new VoltageOut(5);
            MOTOR.setControl(request);
            return;
        }

        VoltageOut request = new VoltageOut(pidController.calculate(carrotPoseidon));
        MOTOR.setControl(request);
    }

    private void stop(){
        MOTOR.stopMotor();
    }
}
















































































