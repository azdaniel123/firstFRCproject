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
     *  make a commandGroup was make the collector collect the game piece, wait 3 second, eject the game piece and wait 3 second again.
     * @return a SequentialCommandGroup was make the collector collect the game piece, wait 3 second, eject the game piece and wait 3 second again.
     */
    public SequentialCommandGroup getCollectThenEjectCommand() {
        return new SequentialCommandGroup(getCollectCommand().withTimeout(3), getEjectCommand().withTimeout(3));
    }

    /**
     * make a command was make the collector collect the game piece.
     * @return a startEndCommend was make the collector collect the game piece and stop the motor.
     */
    public CommandBase getCollectCommand() {return startEnd(this::collect, this::stop);}

    /**
     * make a command was make the collector eject the game piece.
     * @return a startEndCommend was make the collector eject the game piece and stop the motor.
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

