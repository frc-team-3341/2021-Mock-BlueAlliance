// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ArmSetAngle extends CommandBase {
  /** Creates a new ArmSetAngle. */
  private double targetAngle;

  // these constants need to be tested!!
  private double kP = 0.1;
  private double kI = 0;
  private double kD = 0;
  PIDController pid;

  public ArmSetAngle(double angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    targetAngle = angle;
    pid = new PIDController(kP, kI, kD);
    addRequirements(RobotContainer.returnArm());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pid.reset();
    pid.setTolerance(2.0);
    RobotContainer.returnArm().setArmBrake(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double armPowerPID = pid.calculate(RobotContainer.returnArm().getArmAngle(), targetAngle);
    RobotContainer.returnArm().setArmPower(armPowerPID);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.returnArm().setArmPower(0);
    RobotContainer.returnArm().setArmBrake(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // returns true if error is less than 2 degrees
    // see pid.setTolerance() in initialize
    return pid.atSetpoint();
  }
}
