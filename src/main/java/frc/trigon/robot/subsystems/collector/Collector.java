package frc.trigon.robot.subsystems.collector;


import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase {
    private final static Collector INSTANCE = new Collector();
    private final TalonFX motor = CollectorConstants.MOTOR;

    public static Collector getInstance() {
        return INSTANCE;
    }

    private Collector() {
    }

    /**
     * @return a Command that collect wait 3 seconds, eject, wait another 3 seconds, and stop.
     */
    public SequentialCommandGroup getCollectThenEjectCommand() {
        return new SequentialCommandGroup(getCollectCommand().withTimeout(3), getEjectCommand().withTimeout(3));
    }

    /**
     * @return a Command that collect.
     */
    public CommandBase getCollectCommand() {
        return startEnd(this::collect, this::stop);
    }

    /**
     * @return a Command that eject.
     */
    public CommandBase getEjectCommand() {
        return startEnd(this::eject, this::stop);
    }


    private void collect() {
        motor.setVoltage(CollectorConstants.COLLECT_VOLTAGE);
    }

    private void eject() {
        motor.setVoltage(CollectorConstants.EJECT_VOLTAGE);
    }

    private void stop() {
        motor.stopMotor();
    }

}

