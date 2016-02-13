package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * This class describes a driver that uses a gamepad as its input
 */
public class GamepadDriver implements Driver {

    private final float FAST_SPEED = 1f, NORMAL_SPEED = 0.5f, SLOW_SPEED = 0.1f;
    private final float NORMAL_SPIN_SPEED = 0.5f, SLOW_SPIN_SPEED = 0.1f;

    private float _currentSpeed,  _currentSpinSpeed;
    private Gamepad _gamepad;

    public GamepadDriver(Gamepad gamepad){

        //this will setup the gamepad driver
        _currentSpeed = NORMAL_SPEED;
        _currentSpinSpeed = NORMAL_SPIN_SPEED;
        _gamepad = gamepad;

    }

    @Override
    public Movement getMovement() {

        //this will set the spin speed based on whether the right bumper on the gamepad is pressed
        if(_gamepad.right_bumper){

            _currentSpinSpeed = SLOW_SPIN_SPEED;

        } else {

            _currentSpinSpeed = NORMAL_SPIN_SPEED;

        }

        //this will set the movement speed based on whether a new speed button is pressed
        if(_gamepad.y){

            _currentSpeed = FAST_SPEED;

        } else if(_gamepad.x){

            _currentSpeed = NORMAL_SPEED;

        } else if(_gamepad.a){

            _currentSpeed = SLOW_SPEED;

        }

        //this will calculate the movement speeds using the gamepad and the current speeds
        float horizontalSpeed = _gamepad.left_stick_x * _currentSpeed;
        float linearSpeed = _gamepad.left_stick_y * _currentSpeed;
        float spinSpeed = _gamepad.right_stick_x * _currentSpinSpeed;

        //this will return the new movement call
        return new Movement(spinSpeed, linearSpeed, horizontalSpeed);

    }

}
