package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * This class describes an autonomous robot
 */
public class Turtl_AutoOp extends OpMode {

    private DriveTrain _driveTrain;
    private AutoDriver _autoDriver;

    @Override
    public void init() {

        //this will create a drive train with the selected motors
        _driveTrain = new DriveTrain(hardwareMap, "motor_1", "motor_2", "motor_3", "motor_4");

        //this will create a auto driver
        _autoDriver = new AutoDriver();
    }

    @Override
    public void loop() {

        //this will tell the drive train use the auto drivers movement calls to move
        _driveTrain.driveUsing(_autoDriver);

    }

}
