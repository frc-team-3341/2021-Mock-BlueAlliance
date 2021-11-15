// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
  /** Creates a new Camera. 
   * 
   * FRC LabVIEW Dashboard
   * Using the cameraserver on the RoboRIO
   * Read and Process video: cameraServer class
   * 
   * Vision Processing
  */

  //private final UsbCamera camera;
  private int cameraPort = 0;
  
  public Camera() {
    //camera = new UsbCamera("CactusCamera", cameraPort);
  }

  public void putVid(){
    // test just this line b4 going into advanced camera server under "using camera on roboRIO"
    CameraServer.getInstance().startAutomaticCapture();
    //CvSink cvSink = CameraServer.getInstance().getVideo();
    //CvSource outputStream = CameraServer.getInstance().putVideo("CactusCamera", 640, 480);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    putVid();
  }
}
