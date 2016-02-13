package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * This class describes a movement call to the drive train
 */
public class Movement {

    private float _spinSpeed, _linearSpeed, _horizontalSpeed;

    public Movement(float spinSpeed, float linearSpeed, float horizontalSpeed){

        //setup a movement call
        _spinSpeed = spinSpeed;
        _linearSpeed = linearSpeed;
        _horizontalSpeed = horizontalSpeed;

    }

    public float getHorizontalSpeed() {

        //this will return the horizontal speed
        return _horizontalSpeed;

    }

    public float getLinearSpeed() {

        //this will return the linear speed
        return _linearSpeed;

    }

    public float getSpinSpeed() {

        //this will return the spin speed
        return _spinSpeed;

    }

    public void setHorizontalSpeed(float _horizontalSpeed) {

        //this will set the horizontal speed
        this._horizontalSpeed = _horizontalSpeed;

    }

    public void setLinearSpeed(float _linearSpeed) {

        //this will set the linear speed
        this._linearSpeed = _linearSpeed;

    }

    public void setSpinSpeed(float _spinSpeed) {

        //this will set the spin speed
        this._spinSpeed = _spinSpeed;

    }
}
