/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CoreCommands.feederCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;

public class ActivateFeeder extends CommandBase {
  /**
   * Creates a new ActivateFeeder.
   */

  private final FeederSubsystem feeder;
  private final double power;
  private double endtime;
  private final double duration;

  public ActivateFeeder(FeederSubsystem feeder, double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.feeder = feeder;
    this.power = power;
    this.duration = 0;
    addRequirements(feeder);
  }
  /**
   * 
   * @param feeder
   * @param power
   * @param time
   */
  public ActivateFeeder(FeederSubsystem feeder, double power, double duration) {
       // Use addRequirements() here to declare subsystem dependencies.
       this.feeder = feeder;
       this.power = power;
       this.duration = duration;
       addRequirements(feeder);
       this.withTimeout(duration);
  }
  
  // public ActivateFeeder(FeederSubsystem feeder, double power, double time) {
  //   // Use addRequirements() here to declare subsystem dependencies.
  //   this.feeder = feeder;
  //   this.power = power;
  //   this.duration = time;
  //   addRequirements(feeder);
  // }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.endtime = this.duration + Timer.getFPGATimestamp();
    this.feeder.set(power);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.feeder.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (this.duration != 0){
      return Timer.getFPGATimestamp() >= this.endtime;
    }
    return false;
  }
}
