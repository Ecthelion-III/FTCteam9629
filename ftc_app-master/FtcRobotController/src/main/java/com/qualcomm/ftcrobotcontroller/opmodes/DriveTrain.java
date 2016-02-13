package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * This class describes a omni-drive base that will drive using movement calls from drivers
 */
public class DriveTrain {

    private DcMotor _backLeftMotor, _backRightMotor, _frontLeftMotor, _frontRightMotor;

    public DriveTrain(HardwareMap map, String backLeftMotor, String backRightMotor, String frontLeftMotor, String frontRightMotor){

        //this will create the drive train motors from the hardware map
        _backLeftMotor = map.dcMotor.get(backLeftMotor);
        _backRightMotor = map.dcMotor.get(backRightMotor);
        _frontLeftMotor = map.dcMotor.get(frontLeftMotor);
        _frontRightMotor = map.dcMotor.get(frontRightMotor);

    }

    public void driveUsing(Driver... drivers) {

        float backLeftPower = 0, backRightPower = 0, frontLeftPower = 0, frontRightPower = 0;

        //this will add all movement calls from all the drivers into one movement call
        for( Driver driver : drivers){

            //this will get the movement call from the driver
            Movement movement = driver.getMovement();

            //this will calculate each motors power from the movement call. This also clips the power to stop motor wrap around
            backLeftPower += Range.clip(movement.getLinearSpeed() + movement.getLinearSpeed() - movement.getSpinSpeed(), -1, 1);
            backRightPower += Range.clip(-movement.getLinearSpeed() - movement.getLinearSpeed() - movement.getSpinSpeed(), -1, 1);
            frontLeftPower += Range.clip(-movement.getLinearSpeed() + movement.getLinearSpeed() - movement.getSpinSpeed(), -1, 1);
            frontRightPower += Range.clip(movement.getLinearSpeed() - movement.getLinearSpeed() - movement.getSpinSpeed(), -1, 1);

        }

        //this will average the resulting total movement call
        backLeftPower /= drivers.length;
        backRightPower /= drivers.length;
        frontLeftPower /= drivers.length;
        frontRightPower /= drivers.length;

        //this will set the motor powers to the total movement call
        _backLeftMotor.setPower(backLeftPower);
        _backRightMotor.setPower(backRightPower);
        _frontLeftMotor.setPower(frontLeftPower);
        _frontRightMotor.setPower(frontRightPower);

    }

}
