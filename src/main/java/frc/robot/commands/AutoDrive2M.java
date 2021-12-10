// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoDrive2M extends CommandBase {
  /** Creates a new AutoDrive2M. */
  private DriveTrain driveTrain;
  private double distance;
  private double speed;
  private double error;
  private double kP = 0.8;

  public AutoDrive2M(DriveTrain dt, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.distance = distance;
    driveTrain = dt;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("is initialized");
    driveTrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Ticks", driveTrain.getTicks());
    SmartDashboard.putNumber("LeftTicks", driveTrain.getLeftTicks());
    SmartDashboard.putNumber("RightTicks", driveTrain.getRightTicks());
    //Get the current Position of the DriveTrain
    //System.out.println("distance" + distance );
    //System.out.println("position"+ driveTrain.getPosition());
    error = distance - driveTrain.getPosition();
    error = (error / distance)*2;
    speed = error * kP;

    if (speed > 0.7){
      speed = 0.7;

    }

    if (speed < 0.25){
      speed = 0.25;
    }

    SmartDashboard.putNumber("Current Speed", speed);
    SmartDashboard.putNumber("Current Distance", driveTrain.getPosition());
    driveTrain.tankDrive(speed,speed);


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("end");
    driveTrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return (driveTrain.getPosition() >= distance);
  }
}
