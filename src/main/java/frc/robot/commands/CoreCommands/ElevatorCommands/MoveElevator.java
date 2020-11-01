/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CoreCommands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;
import jdk.nashorn.api.tree.Tree;

public class MoveElevator extends CommandBase {
  /**
   * Creates a new MoveElevator.
   */
  private final ElevatorSubsystem elevator;
  private int hight;
  private final int changeHight;
  private final int endpos;
  private final boolean isUp;
  private final double power;


  public MoveElevator(ElevatorSubsystem elevator, int changeHight, double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.elevator = elevator;
    this.hight = elevator.getposition();
    this.power = power;
    this.changeHight = changeHight;
    this.endpos = this.changeHight + this.hight;
    addRequirements(elevator);

    if(this.changeHight>0){
      this.isUp = true;
    }
    else{
      this.isUp = false;
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(isUp){
      this.elevator.set(this.power);
    }
    else{
      this.elevator.set(-this.power);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.elevator.set(power);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(this.isUp){
      return this.endpos <= this.elevator.getposition();
    }
    else{
      return this.endpos >= this.elevator.getposition();
    }
  }
}
