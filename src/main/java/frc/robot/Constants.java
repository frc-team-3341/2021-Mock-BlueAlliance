// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static int leftJoy = 0;
    public static int rightJoy = 1;

    //change these ports as necessary--look at driverstation to find correct ports
    public static int angle1Button = 8;
    public static int angle2Button = 8;
    public static int angle3Button = 8;

    public static final class DriveTrainPorts {
        public static final int LeftDriveTalonPort = 2;
        public static final int RightDriveTalonPort = 3;
    } 

    public static final class JoystickAxis {
        public static final int YAxis = 1;
        public static final int XAxis = 0;
    } 


    public static final class USBOrder {
        public static final int Zero = 0;
        public static final int One = 1;

    }

    public static final class ArmPorts{
        public static final int ArmPort = 4;
    }
}
