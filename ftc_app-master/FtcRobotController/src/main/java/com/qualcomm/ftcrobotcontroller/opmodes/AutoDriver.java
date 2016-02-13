package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * This class describes a driver that will driver forward then spin
 */
public class AutoDriver implements Driver {

    //this creates the states of the program
    private enum AutoState{
        Start, DriveForward, Spin, Stop
    }

    private AutoState _state;
    private long _stateStartTime;

    public AutoDriver(){

        //this will set the state to the beginning state
        changeState(AutoState.Start);

    }

    private void changeState(AutoState newState){

        //this will change the state and update the start time
        _state = newState;
        _stateStartTime = System.nanoTime();

    }

    private long seconds(int seconds){

        //this will convert seconds to nanoseconds
        return seconds * 1000000000;

    }

    private long getTimeFromStateChange(){

        //this will return the time in nanoseconds from the last state change
        return System.nanoTime() - _stateStartTime;

    }

    @Override
    public Movement getMovement() {

        //this will create a movement call
        Movement movement = new Movement(0, 0, 0);

        //this will change the movement call based on the state
        switch (_state){

            case Start:

                //this will change the state to the drive forward state
                changeState(AutoState.DriveForward);

                break;

            case DriveForward:

                if(getTimeFromStateChange() > seconds(2)){

                    //this will change the state to the stopped state if 2 seconds have passed
                    changeState(AutoState.Spin);

                } else {

                    //this will set the movement call's linear speed to 0.5
                    movement.setLinearSpeed(0.5f);

                }

                break;

            case Spin:

                if(getTimeFromStateChange() > seconds(5)){

                    //this will change the state to the stopped state if 5 seconds have passed
                    changeState(AutoState.Stop);

                } else {

                    //this will set the movement call's spin speed to 0.5
                    movement.setSpinSpeed(0.5f);

                }

                break;

            case Stop:

                //this will do nothing

                break;

        }

        //this will return the movement call
        return movement;

    }
}
