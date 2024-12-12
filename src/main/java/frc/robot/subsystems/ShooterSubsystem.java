package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class ShooterSubsystem extends SubsystemBase {
    private CANSparkMax leftMotor, rightMotor;
    private double voltage = 12;

    
    public ShooterSubsystem(){

        leftMotor = new CANSparkMax(Ports.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);
        rightMotor = new CANSparkMax(Ports.SHOOTER_MOTOR_RIGHT, MotorType.kBrushless);

        leftMotor.setInverted(false);
        rightMotor.follow(leftMotor, true);

        rightMotor.enableVoltageCompensation(voltage);
        leftMotor.enableVoltageCompensation(voltage);

    }

    public void setMotorPower(double motorPower, String reason){
        
        leftMotor.set(motorPower);

    }

}
