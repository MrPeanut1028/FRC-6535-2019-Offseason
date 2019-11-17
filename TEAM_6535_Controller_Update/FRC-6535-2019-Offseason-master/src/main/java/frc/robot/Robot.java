/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//2019 OFFSEASON CODE
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

//import edu.wpi.first.wpilibj.CAN;
//import edu.wpi.first.wpilibj.Servo;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  //private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(new Spark(0), new Spark(1));  
  private final Joystick m_stick = new Joystick(0);  
  private final Timer m_timer = new Timer();
  private final Spark rota1 = new Spark(2);
  private final Spark rota2 = new Spark (3);
  private final Spark input1 = new Spark (4);
  //private final Spark flipper = new Spark (5);
  //private static int upButton = 4;
  //private static int downButton = 6;
  Compressor pressBoy = new Compressor(0);
  //Solenoid exampleSolenoid = new Solenoid(1);
  DoubleSolenoid rearPistons = new DoubleSolenoid(1, 0);
  DoubleSolenoid frontPiston = new DoubleSolenoid(3, 2);
  boolean enabledCompr = pressBoy.enabled();
  //boolean pressureSwitch = pressBoy.getPressureSwitchValue();
  double currentCompr = pressBoy.getCompressorCurrent();
  public boolean pneuLoop = false;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    //starts the dual cameras
    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);
    //auto start (disabled atm)
    //pressBoy.setClosedLoopControl(true);
    pressBoy.setClosedLoopControl(false);
    
   
  }

  
  @Override
  public void robotPeriodic() {

    //this code will always run when the robot is enabled in any setting

    enabledCompr = pressBoy.enabled();
    //pressureSwitch = pressBoy.getPressureSwitchValue();
    currentCompr = pressBoy.getCompressorCurrent();
    //SmartDashboard.putBoolean("Pneumatics Loop Enable", closedLoopCont);
    SmartDashboard.putBoolean("Compressor Status", enabledCompr);
    //SmartDashboard.putBoolean("Pressure Switch", pressureSwitch);
    SmartDashboard.putNumber("Compressor Current", currentCompr);
  }

  
  @Override
  public void autonomousInit() {
   
    m_timer.reset();
    m_timer.start();
  }

  
  @Override
  public void autonomousPeriodic() {
     //drive code
    if(m_stick.getRawAxis(3)>0 && m_stick.getRawAxis(2)==0){
     m_robotDrive.arcadeDrive(m_stick.getRawAxis(3), m_stick.getRawAxis(0))
    }
    else if(m_stick.getRawAxis(2)>0 && m_stick.getRawAxis(3)==0{
     m_robotDrive.arcadeDrive(m_stick.getRawAxis(2) * -1, m_stick.getRawAxis(0))
    }
     //arm rotation mvmt
     if (m_stick.getRawButton(3)){
       rota1.set(0.4);
       rota2.set(0.4);
       //up
     }
     else if(m_stick.getRawButton(4)){
       rota1.set(-0.4);
       rota2.set(-0.4);
       //down
     }
     else{
       rota1.set(0);
       rota2.set(0);
       //all stop
     }
 
     //input code (of arm???)
 
     if (m_stick.getRawButton(1)){
       input1.set(0.7);
       //forward
     }
     else if(m_stick.getRawButton(2)){
       input1.set(-0.7);
       //reverse
     }
     else{
       input1.set(0);
       //all stop
     }
  }
  @Override
  public void teleopInit() {
    m_timer.stop();
    m_timer.reset();
    
  }

  @Override
  
  public void teleopPeriodic() {
    //drive code
    if(m_stick.getRawAxis(3)>0 && m_stick.getRawAxis(2)>0){
      system.out.print("SthAP PressiNG boTH BuTuNS!")
    }
    else if(m_stick.getRawAxis(3)>0 && m_stick.getRawAxis(2)==0){
     m_robotDrive.arcadeDrive(m_stick.getRawAxis(3), m_stick.getRawAxis(0))
    }
    else if(m_stick.getRawAxis(2)>0 && m_stick.getRawAxis(3)==0{
     m_robotDrive.arcadeDrive(m_stick.getRawAxis(2) * -1, m_stick.getRawAxis(0))
    }
     //arm rotation mvmt
     if (m_stick.getRawButton(3)){
       rota1.set(0.4);
       rota2.set(0.4);
       //up
     }
     else if(m_stick.getRawButton(4)){
       rota1.set(-0.4);
       rota2.set(-0.4);
      //down
    }
    else{
      rota1.set(0);
      rota2.set(0);
      //all stop
    }

    //input code (of arm???)

    if (m_stick.getRawButton(1)){
      input1.set(0.7);
      //forward
    }
    else if(m_stick.getRawButton(2)){
      input1.set(-0.7);
      //reverse
    }
    else{
      input1.set(0);
      //all stop
    }
    

    
    //pneumatic loop code
    if(m_stick.getRawButton(7)){
      pressBoy.setClosedLoopControl(true);
      pneuLoop = true;
      //SmartDashboard.putBoolean("Pneu Loop Enabled", true);
    }
    else if(m_stick.getRawButton(8)){
      pressBoy.setClosedLoopControl(false);
      pneuLoop = false;
      //SmartDashboard.putBoolean("Pneu Loop Disabled", false);
    }
    SmartDashboard.putBoolean("Pneu Loop Enabled", pneuLoop);


    //Rear Dual Legs Up and Down
    
    
    if(m_stick.getPOV()==0){
      rearPistons.set(DoubleSolenoid.Value.kReverse);
      frontPiston.set(DoubleSolenoid.Value.kReverse);
    }
    else if (m_stick.getPOV()==270){
      rearPistons.set(DoubleSolenoid.Value.kForward);
    }
    else if(m_stick.getPOV(90)){
      frontPiston.set(DoubleSolenoid.Value.kForward);
    }

  }

 
  

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    //manual flipper mvmt
    /*
    if(m_stick.getRawButton(upButton)){
      flipper.set(0.8);
    }
    else if(m_stick.getRawButton(downButton)){
      flipper.set(-0.8);
    }
    else {
      flipper.set(0);
    }
    */


  }
}
