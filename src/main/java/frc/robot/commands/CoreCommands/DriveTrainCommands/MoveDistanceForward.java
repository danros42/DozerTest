/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CoreCommands.DriveTrainCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubSystem;

public class MoveDistanceForward extends CommandBase {
  
  private double speed;
  private final double distance;
  private final DriveTrainSubSystem driveTrain;

/**
   * Creates a new MoveForward.
   */
  public MoveDistanceForward(double distance, double speed, DriveTrainSubSystem driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    this.distance = distance;
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // deg can't be exual to 0
    if(distance > 0){
      this.driveTrain.tankDrive(this.speed, this.speed , 1);
    }
    else {
      this.driveTrain.tankDrive(-this.speed, -this.speed , 1);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.driveTrain.tankDrive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
