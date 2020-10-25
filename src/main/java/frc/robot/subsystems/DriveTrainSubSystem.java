/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import poroslib.subsystems.DiffDrivetrain;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubSystem extends DiffDrivetrain {
  
  private WPI_TalonSRX masterLeft;
  private WPI_TalonSRX masterRight;
  private WPI_TalonSRX middleLeft;
  private WPI_TalonSRX middleRight;
  private WPI_VictorSPX rearLeft;
  private WPI_VictorSPX rearRight;
  private AHRS navx;

  
  /**
   * Creates a new DriveTrainSystem.
   */
  public DriveTrainSubSystem() {
    super(new WPI_TalonSRX(0), new WPI_TalonSRX(0));
    this.configMotors();
  }

  private void configMotors() {
    this.createMotors();
    this.configFollowers();
    this.setNeutralModes();
    this.setInvertions();
    this.setSensors();
  }

  private void createMotors() {
    this.masterLeft = (WPI_TalonSRX)this.leftController;
    this.masterRight = (WPI_TalonSRX)this.rightController;
    this.middleLeft = new WPI_TalonSRX(0);
    this.middleRight = new WPI_TalonSRX(0);
    this.rearLeft = new WPI_VictorSPX(0);
    this.rearRight = new WPI_VictorSPX(0);
  }

  private void configFollowers() {
    this.middleLeft.follow(masterLeft);
    this.rearLeft.follow(masterLeft);
    this.middleRight.follow(masterRight);
    this.rearRight.follow(masterRight);
  }

  private void setNeutralModes() {
    NeutralMode nm = NeutralMode.Brake;

    this.masterLeft.setNeutralMode(nm);
    this.masterRight.setNeutralMode(nm);
    this.middleLeft.setNeutralMode(nm);
    this.middleRight.setNeutralMode(nm);
    this.rearLeft.setNeutralMode(nm);
    this.rearRight.setNeutralMode(nm);
  }

  private void setInvertions() {
    this.masterLeft.setInverted(InvertType.None);
    this.masterRight.setInverted(InvertType.None);
    this.middleLeft.setInverted(InvertType.FollowMaster);
    this.middleRight.setInverted(InvertType.FollowMaster);
    this.rearLeft.setInverted(InvertType.FollowMaster);
    this.rearRight.setInverted(InvertType.FollowMaster);
  }

  private void setSensors(){
    this.masterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    this.masterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    this.navx = new AHRS(SPI.Port.kMXP);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public int getRawLeftPosition() {

    return this.masterLeft.getSelectedSensorPosition();
  }

  @Override
  public int getRawRightPosition() {
    
    return this.masterRight.getSelectedSensorPosition();
  }

  @Override
  public double getHeading() {
    return this.navx.getYaw();
  }
}
