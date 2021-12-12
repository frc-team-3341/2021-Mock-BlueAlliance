// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.ArmSetAngle;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ResetArm;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoDrive2M;
import frc.robot.commands.DriveToALine;
import frc.robot.commands.TankDrive;
import frc.robot.commands.Test;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private static DriveTrain _driveTrain;
  private final Joystick _leftJoystick;
  private final Joystick _rightJoystick;
 // private final TankDrive _tankDrive;
  private final ArcadeDrive _arcadeDrive;
  private static DriveToALine _driveToLine;
  private static AutoDrive2M test;
  private static Test test1;


  private static final Arm m_arm = new Arm();
  public static Joystick leftJoy = new Joystick(Constants.leftJoy);
  public static Joystick rightJoy = new Joystick(Constants.rightJoy);
  public static JoystickButton arm1, arm2, arm3, reset, crate;

  // fill in with appropriate angles needed for mechanism
  private double[] armAngles = {15, 28, 45, 89};

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  
  public RobotContainer() {
    // Configure the button bindings
    _driveTrain = new DriveTrain();
    _leftJoystick = new Joystick(Constants.USBOrder.Zero);
    _rightJoystick = new Joystick(Constants.USBOrder.One);
    // _tankDrive = new TankDrive(_driveTrain, _leftJoystick, _rightJoystick);
    _arcadeDrive = new ArcadeDrive(_driveTrain, _leftJoystick);
    _driveTrain.setDefaultCommand(_arcadeDrive);
    configureButtonBindings();
    _driveToLine = new DriveToALine();
    test = new AutoDrive2M(_driveTrain, 50);
    test1 = new Test();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    arm1 = new JoystickButton(leftJoy, Constants.angle1Button);
    arm2 = new JoystickButton(leftJoy, Constants.angle2Button);
    arm3 = new JoystickButton(leftJoy, Constants.angle3Button);
    reset = new JoystickButton(leftJoy, Constants.resetButton);
    crate = new JoystickButton(leftJoy, Constants.crateButton);

    crate.toggleWhenPressed(new ArmSetAngle(armAngles[0]));
    arm1.toggleWhenPressed(new ArmSetAngle(armAngles[1]));
    arm2.toggleWhenPressed(new ArmSetAngle(armAngles[2]));
    arm3.toggleWhenPressed(new ArmSetAngle(armAngles[3]));
    reset.toggleWhenPressed(new ResetArm());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static DriveTrain getDriveTrain(){
    return _driveTrain;
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return test1;
  }

  public static Arm returnArm(){
    return m_arm;
  }

  public static Joystick getLeftJoy(){
    return leftJoy;
  }

  public static Joystick getRightJoy(){
    return rightJoy;
  }
}
