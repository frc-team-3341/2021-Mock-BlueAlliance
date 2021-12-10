// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private final WPI_TalonSRX arm;

  // need to get correct armGearRatio from hardware--this value is inaccurate
  private double armGearRatio = 1.0;

  public Arm() {
    //be sure to go in pheonixTuner and reconfig + edit this port in the code
    arm = new WPI_TalonSRX(Constants.ArmPorts.ArmPort);
    arm.configFactoryDefault();
    //this might need to be changed to true if ticks are printing negative when you want them to be positive
    arm.setInverted(false);
    arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
    arm.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    arm.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
  }

  // getting reading of absolute encoder
  public double getArmTicks(){
    return arm.getSelectedSensorPosition() * -1;
  }

  // this method will definitely need testing
  // suggest just returning getArmTicks first
  public double getArmAngle(){
    // note: returning angle in degrees
    return ((arm.getSelectedSensorPosition() * -1) / 4096.0) * armGearRatio * 360;
  }

  // resetting absolute encoder
  public void resetArm(){
    arm.setSelectedSensorPosition(0, 0, 10);
  }

  // setting power to arm talon
  public void setArmPower(double power){
    SmartDashboard.putNumber("subsystem power: ", power);
    arm.set(ControlMode.PercentOutput, power * -1);
  }

  // different settings of talon
  // brake: mechanism can't move freely
  // coast: mechanism can move freely (free-spin)
  public void setArmBrake(boolean brake){
    if(brake){
      arm.setNeutralMode(NeutralMode.Brake);
    }else{
      arm.setNeutralMode(NeutralMode.Coast);
    }
  }

  public int isFwdLSClosed(){
    return arm.isFwdLimitSwitchClosed();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run--meaning you can easily test from here!

    // putting values on smartdashboard to help w/ debugging
    SmartDashboard.putNumber("Arm Ticks: ", getArmTicks());
    SmartDashboard.putNumber("Current Angle: ", getArmAngle());
    SmartDashboard.putNumber("Fwd LS: ", arm.isFwdLimitSwitchClosed());
    SmartDashboard.putNumber("Rev LS: ", arm.isRevLimitSwitchClosed());

    //setArmPower(-RobotContainer.getLeftJoy().getY());

    // resetting absolute encoder when arm is upright
    // check to figure out if it's when Rev or Fwd limit switch is open
    // if Rev change method to arm.isRevLimitSwitchClosed()
    if(arm.isFwdLimitSwitchClosed() == 0){
      resetArm();
    }
  }
}
