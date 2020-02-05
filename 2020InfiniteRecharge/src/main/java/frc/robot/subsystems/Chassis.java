/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  /**
   * Creates a new Chassis.
   */

  TalonSRX lf;
  TalonSRX lb;
  TalonSRX rf;
  TalonSRX rb;
  SpeedControllerGroup left;
  SpeedControllerGroup right;
  ADXRS450_Gyro gyro;
  public int timeoutMs = 100;

  public Chassis() {
    lf = new TalonSRX(0);
    lb = new TalonSRX(1);
    rf = new TalonSRX(2);
    rb = new TalonSRX(3);
    gyro = new ADXRS450_Gyro();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    lb.follow(lf);
    rb.follow(rf);

  }

  public void tankDrive(double left, double right){
    lf.setNeutralMode(NeutralMode.Coast);
    rf.setNeutralMode(NeutralMode.Coast);

    lb.setNeutralMode(NeutralMode.Coast);
    rb.setNeutralMode(NeutralMode.Coast);

    lf.set(ControlMode.PercentOutput, left);
    rf.set(ControlMode.PercentOutput, right);
  }

  public void stop(){
    lf.setNeutralMode(NeutralMode.Brake);
    rf.setNeutralMode(NeutralMode.Brake);

    lb.setNeutralMode(NeutralMode.Brake);
    rb.setNeutralMode(NeutralMode.Brake);

    lf.set(ControlMode.PercentOutput, 0);
    rf.set(ControlMode.PercentOutput, 0);
  }

  public void driveStrightDist(double dist){
    lf.setNeutralMode(NeutralMode.Brake);
    rf.setNeutralMode(NeutralMode.Brake);

    lb.setNeutralMode(NeutralMode.Brake);
    rb.setNeutralMode(NeutralMode.Brake);

    lf.set(ControlMode.MotionMagic, (-dist/Constants.wheelCircumference)*(Constants.talonTicksPerRot));
    rf.set(ControlMode.MotionMagic, (-dist/Constants.wheelCircumference)*(Constants.talonTicksPerRot));
  }

  public void resetEnc(){
    lf.setSelectedSensorPosition(0, 0, timeoutMs);
    rf.setSelectedSensorPosition(0, 0, timeoutMs);
  }

}
