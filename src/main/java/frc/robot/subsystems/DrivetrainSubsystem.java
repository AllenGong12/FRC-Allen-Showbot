package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.RobotContainer;

public class DrivetrainSubsystem {
    
    private TalonFX rightDriveFalconMain, leftDriveFalconMain, rightDriveFalconSub, leftDriveFalconSub;
    private double speedMod;

    public DrivetrainSubsystem(){

        rightDriveFalconMain = new TalonFX(Ports.RIGHT_DRIVE_FALCON_MAIN, "Right Main Drive Motor");

        leftDriveFalconMain = new TalonFX(Ports.LEFT_DRIVE_FALCON_MAIN, "Left Main Drive Motor");
        
        rightDriveFalconSub = new TalonFX(Ports.RIGHT_DRIVE_FALCON_SUB, "Right Sub Drive Motor");
        
        leftDriveFalconSub = new TalonFX(Ports.LEFT_DRIVE_FALCON_SUB, "Left Sub Drive Motor");

        leftDriveFalconSub.setControl(new Follower(leftDriveFalconMain.getDeviceID(), false));

        leftDriveFalconMain.setInverted(true);

        rightDriveFalconSub.setControl(new Follower(rightDriveFalconMain.getDeviceID(), false));

        leftDriveFalconMain.setNeutralMode(NeutralModeValue.Brake);

        rightDriveFalconMain.setNeutralMode(NeutralModeValue.Brake);
    }

    public double powerCap(double desiredPower){
        return Math.max(Math.min(1, desiredPower), -1);
    }

    public double powerRamping(double desiredPower, double startingPower){

        if (desiredPower > startingPower){

            Math.min(desiredPower, startingPower + Constants.MAX_POWER_CHANGE);

        }

        else if (desiredPower < startingPower){

            Math.max(desiredPower, startingPower - Constants.MAX_POWER_CHANGE);

        }

        return startingPower;
    }

    public void setDriveTrainMotorPower(double leftMotorDesiredPower, double rightMotorDesiredPower){

        speedMod = 0.5;

        double rightPowerCurrent = rightDriveFalconMain.get();
        double leftPowerCurrent = leftDriveFalconMain.get();

        rightMotorDesiredPower *= speedMod;
        leftMotorDesiredPower *= speedMod;

        leftMotorDesiredPower = powerCap(leftMotorDesiredPower);
        rightMotorDesiredPower = powerCap(rightMotorDesiredPower);

        rightDriveFalconMain.set(rightMotorDesiredPower);
        leftDriveFalconMain.set(leftMotorDesiredPower);
    }

    
}
