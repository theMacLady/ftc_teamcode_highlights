
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Locale;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Disabled
@Autonomous
public class FFAutoSource extends LinearOpMode {
//only exists for other classes to extend. For other classes to Inherit
 HardwareFreightFrenzy robot = new HardwareFreightFrenzy();
    
    DcMotor FrontLeft, FrontRight, BackLeft, BackRight;
    BNO055IMU imu;


    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        
        FrontLeft = robot.FrontLeft;
        FrontRight = robot.FrontRight;
        BackLeft = robot.BackLeft;
        BackRight = robot.BackRight;
        imu = robot.imu;
        FrontRight.setDirection(DcMotor.Direction.REVERSE);
        BackRight.setDirection (DcMotor.Direction.REVERSE);
       // robot.Dispenser.setPosition(0.47);
        
        Orientation angles;
        Acceleration gravity;
       
        waitForStart();
        
    }
    
    public void turnLeft90(){
        FrontLeft.setPower(-0.3);
        FrontRight.setPower(0.3);
        BackLeft.setPower(-0.3);
        BackRight.setPower(0.3);
        sleep(1450);
        stopNOW();
    }
    public void turnRight90(){
        FrontLeft.setPower(0.3);
        FrontRight.setPower(-0.3);
        BackLeft.setPower(0.3);
        BackRight.setPower(-0.3);
        sleep(1350);
        stopNOW();
    }

   
    
    

    public void stopNOW(){
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }
    
    
    //make strafeLeftPower, strafeRightPower, straightPower, rightPower, leftPower to set given power
    //for motors and run

    
    public void turnLeft(double angleMeasure){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double startAngle = angles.firstAngle;
        double goalAngle = startAngle + angleMeasure;
        if(goalAngle > 180){
            goalAngle -= 360;
            while((angles.firstAngle <= goalAngle || angles.firstAngle >= startAngle) && opModeIsActive()){
                FrontLeft.setPower(-0.25);
                FrontRight.setPower(0.25);
                BackLeft.setPower(-0.25);
                BackRight.setPower(0.25);
                telemetry.addData("Angle1", String.format(Locale.US, "%.02f", angles.firstAngle));
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.update();
            }
        }
        else{
           while(angles.firstAngle <= goalAngle && opModeIsActive()){
                FrontLeft.setPower(-0.25);
                FrontRight.setPower(0.25);
                BackLeft.setPower(-0.25);
                BackRight.setPower(0.25);
                telemetry.addData("Angle1", String.format(Locale.US, "%.02f", angles.firstAngle));
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.update();
           }
        }
      stopNOW();
    }
    
    
    
    public void turnRight(double angleMeasure){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double startAngle = angles.firstAngle;
        double goalAngle = startAngle - angleMeasure;
        if(goalAngle < -180){
            goalAngle += 360;
            while((angles.firstAngle >= goalAngle || angles.firstAngle <= startAngle) && opModeIsActive()){
                FrontLeft.setPower(0.25);
                FrontRight.setPower(-0.25);
                BackLeft.setPower(0.25);
                BackRight.setPower(-0.25);
                telemetry.addData("Angle1", String.format(Locale.US, "%.02f", angles.firstAngle));
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.update();
            }
        }
        else{
           while(angles.firstAngle >= goalAngle && opModeIsActive()){
                FrontLeft.setPower(0.25);
                FrontRight.setPower(-0.25);
                BackLeft.setPower(0.25);
                BackRight.setPower(-0.25);
                telemetry.addData("Angle1", String.format(Locale.US, "%.02f", angles.firstAngle));
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.update();
           }
        }
       stopNOW();
    }
    
   /* public void foreSight(int distance){
        while(opModeIsActive() && !(ds2.getDistance(DistanceUnit.CM) < distance)) {
            FrontLeft.setPower(0.3);
            FrontRight.setPower(0.3);
            BackLeft.setPower(0.3);
            BackRight.setPower(0.3);
            telemetry.addData("Distance: ", ds2.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
        stopNOW();
    }*/
    
    public void resetWheelEncoders(){
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    
    
    public void straightEncoder(int distance){
        FrontLeft.setTargetPosition(distance);
        FrontRight.setTargetPosition(distance);
        BackLeft.setTargetPosition(distance);
        BackRight.setTargetPosition(distance);
        resetWheelEncoders();
        // set both motors to 25% power. Movement will start.
        int M = distance / Math.abs(distance);
        FrontLeft.setPower(0.3*M);
        FrontRight.setPower(0.3*M);
        BackLeft.setPower(0.3*M);
        BackRight.setPower(0.3*M);
       // wait while opmode is active and left motor is busy running to position.
        while (opModeIsActive() && (FrontLeft.isBusy())) {
           telemetry.addData("Position" , FrontLeft.getCurrentPosition());
           telemetry.addData("Position2" , FrontRight.getCurrentPosition());
           telemetry.addData("Position3" , BackLeft.getCurrentPosition());
           telemetry.addData("Position4" , BackRight.getCurrentPosition());
           telemetry.update();
           idle();
        }
       stopNOW();
    }
    
    public void turnEncoder(int distance){
        FrontLeft.setTargetPosition(-distance);
        FrontRight.setTargetPosition(distance);
        BackLeft.setTargetPosition(-distance);
        BackRight.setTargetPosition(distance);
        resetWheelEncoders();
        // set both motors to 25% power. Movement will start.
        int M = distance / Math.abs(distance);
        FrontLeft.setPower(-0.25 * M);
        FrontRight.setPower(0.25 * M);
        BackLeft.setPower(-0.25* M);
        BackRight.setPower(0.25* M);
        // wait while opmode is active and left motor is busy running to position.
        while (opModeIsActive() && (FrontLeft.isBusy())) {
           idle();
        }
        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.
        stopNOW();
    }
    
    public void strafeEncoder(int distance){
        FrontLeft.setTargetPosition(-distance);
        FrontRight.setTargetPosition(distance);
        BackLeft.setTargetPosition(distance);
        BackRight.setTargetPosition(-distance);
        resetWheelEncoders();
        // set both motors to 25% power. Movement will start.
        int M = distance / Math.abs(distance);
        FrontLeft.setPower(-0.25*M);
        FrontRight.setPower(0.25*M);
        BackLeft.setPower(0.25*M);
        BackRight.setPower(-0.25*M);
        // wait while opmode is active and left motor is busy running to position.
        while (opModeIsActive() && (FrontLeft.isBusy())) {
           idle();
        }
        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.
       stopNOW();
    }
    
    
    public void strafeTime(int milliseconds){
         if(milliseconds > 0){
        FrontLeft.setPower(-0.4);
        FrontRight.setPower(0.42);
        BackLeft.setPower(0.4);
        BackRight.setPower(-0.38);
        sleep(milliseconds);
        //strafe right
       }
       else if(milliseconds < 0){
        FrontLeft.setPower(0.4);
        FrontRight.setPower(-0.4);
        BackLeft.setPower(-0.4);
        BackRight.setPower(0.4);
        sleep(-milliseconds);
        //strafe left
       }
        stopNOW();
    }
    
    
    public void straightTime(int milliseconds){
       if(milliseconds > 0){
        FrontLeft.setPower(0.3);
        FrontRight.setPower(0.3);
        BackLeft.setPower(0.3);
        BackRight.setPower(0.3);
        sleep(milliseconds);
       }
       else if(milliseconds < 0){
        FrontLeft.setPower(-0.3);
        FrontRight.setPower(-0.3);
        BackLeft.setPower(-0.3);
        BackRight.setPower(-0.3);
        sleep(-milliseconds);
       }
       stopNOW();
    }
    
    public void straightTimePower(int milliseconds, int power){
       if((milliseconds > 0) && (power == 1)){
        FrontLeft.setPower(0.3);
        FrontRight.setPower(0.3);
        BackLeft.setPower(0.3);
        BackRight.setPower(0.3);
        sleep(milliseconds);
       }
       else if((milliseconds < 0) && (power == 1)){
        FrontLeft.setPower(-0.3);
        FrontRight.setPower(-0.3);
        BackLeft.setPower(-0.3);
        BackRight.setPower(-0.3);
        sleep(-milliseconds);
       }
       else if((milliseconds > 0) && (power == 2)){
        FrontLeft.setPower(0.35);
        FrontRight.setPower(0.35);
        BackLeft.setPower(0.35);
        BackRight.setPower(0.35);
        sleep(milliseconds);   
       }
       else if((milliseconds < 0) && (power == 2)){
        FrontLeft.setPower(-0.35);
        FrontRight.setPower(-0.35);
        BackLeft.setPower(-0.35);
        BackRight.setPower(-0.35);
        sleep(-milliseconds);  
       }
       else if((milliseconds > 0) && (power == 3)){
        FrontLeft.setPower(0.4);
        FrontRight.setPower(0.4);
        BackLeft.setPower(0.4);
        BackRight.setPower(0.4);
        sleep(milliseconds);   
       }
       else if((milliseconds < 0) && (power == 3)){
        FrontLeft.setPower(-0.4);
        FrontRight.setPower(-0.4);
        BackLeft.setPower(-0.4);
        BackRight.setPower(-0.4);
        sleep(-milliseconds);  
       }
       else if((milliseconds > 0) && (power == 4)){
        FrontLeft.setPower(0.45);
        FrontRight.setPower(0.45);
        BackLeft.setPower(0.45);
        BackRight.setPower(0.45);
        sleep(milliseconds);   
       }
       else if((milliseconds < 0) && (power == 4)){
        FrontLeft.setPower(-0.45);
        FrontRight.setPower(-0.45);
        BackLeft.setPower(-0.45);
        BackRight.setPower(-0.45);
        sleep(-milliseconds);  
       }
       else if((milliseconds > 0) && (power == 5)){
        FrontLeft.setPower(0.5);
        FrontRight.setPower(0.5);
        BackLeft.setPower(0.5);
        BackRight.setPower(0.5);
        sleep(milliseconds);   
       }
       else if((milliseconds < 0) && (power == 5)){
        FrontLeft.setPower(-0.5);
        FrontRight.setPower(-0.5);
        BackLeft.setPower(-0.5);
        BackRight.setPower(-0.5);
        sleep(-milliseconds);  
       }
       else if((milliseconds > 0) && (power == 6)){
        FrontLeft.setPower(0.55);
        FrontRight.setPower(0.55);
        BackLeft.setPower(0.55);
        BackRight.setPower(0.55);
        sleep(milliseconds);   
       }
       else if((milliseconds < 0) && (power == 6)){
        FrontLeft.setPower(-0.55);
        FrontRight.setPower(-0.55);
        BackLeft.setPower(-0.55);
        BackRight.setPower(-0.55);
        sleep(-milliseconds);  
       }
       else if((milliseconds > 0) && (power == 7)){
        FrontLeft.setPower(0.6);
        FrontRight.setPower(0.6);
        BackLeft.setPower(0.6);
        BackRight.setPower(0.6);
        sleep(milliseconds);   
       }
       else if((milliseconds < 0) && (power == 7)){
        FrontLeft.setPower(-0.6);
        FrontRight.setPower(-0.6);
        BackLeft.setPower(-0.6);
        BackRight.setPower(-0.6);
        sleep(-milliseconds);  
       }
       stopNOW();
    }
    
    
    public void correctLeftTime(int milliseconds){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double startAngle = angles.firstAngle;
        long startTime = System.currentTimeMillis();
        while(opModeIsActive() &&  System.currentTimeMillis() < startTime + milliseconds){      
        
            double adj = Math.abs((angles.firstAngle - startAngle)/5.0);
            double angle = angles.firstAngle;
            if(angle > startAngle || angle + 360 > startAngle && angle < -179){
                FrontLeft.setPower(-0.4 - adj);
                FrontRight.setPower(0.4 - adj);
                BackLeft.setPower(0.4 + adj);
                BackRight.setPower(-0.4 + adj);
            }
            else if(angles.firstAngle < startAngle || angle - 360 < startAngle  && angle > 179){
                FrontLeft.setPower(-0.4 + adj);
                FrontRight.setPower(0.4 + adj);
                BackLeft.setPower(0.4 - adj);
                BackRight.setPower(-0.4 - adj);
            }
            else{
                FrontLeft.setPower(-0.4);
                FrontRight.setPower(0.4);
                BackLeft.setPower(0.4);
                BackRight.setPower(-0.4);
            }
            telemetry.addData("Angle1", String.format(Locale.US, "%.02f", angles.firstAngle));
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            telemetry.update();
        }
        stopNOW();
     }
    
    
    public void correctRightTime(int milliseconds){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double startAngle = angles.firstAngle;
        long startTime = System.currentTimeMillis();
        while(opModeIsActive() &&  System.currentTimeMillis() < startTime + milliseconds){      
        
            double adj = Math.abs((angles.firstAngle - startAngle)/5.0);
  
            double angle = angles.firstAngle;
            if(angle > startAngle || angle + 360 > startAngle && angle < -179){
                FrontLeft.setPower(0.4 - adj);
                FrontRight.setPower(-0.4 - adj);
                BackLeft.setPower(-0.4 + adj);
                BackRight.setPower(0.4 + adj);
            }
            else if(angles.firstAngle < startAngle || angle - 360 < startAngle  && angle > 179){
                FrontLeft.setPower(0.4 + adj);
                FrontRight.setPower(-0.4 + adj);
                BackLeft.setPower(-0.4 - adj);
                BackRight.setPower(0.4 - adj);
            }
            else{
                FrontLeft.setPower(0.4);
                FrontRight.setPower(-0.4);
                BackLeft.setPower(-0.4);
                BackRight.setPower(0.4);
            }
            telemetry.addData("Angle1", String.format(Locale.US, "%.02f", angles.firstAngle));
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            telemetry.update();
        }
        stopNOW();
    }
    
    
    public void correctStraightTime(int milliseconds){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double startAngle = angles.firstAngle;
        long startTime = System.currentTimeMillis();
        while(opModeIsActive() &&  System.currentTimeMillis() < startTime + milliseconds){      
        
            double adj = Math.abs((angles.firstAngle - startAngle)/25.0);
            double angle = angles.firstAngle;
            if(angle > startAngle || angle + 360 > startAngle && angle < -179){
                FrontLeft.setPower(0.3 + adj);
                FrontRight.setPower(0.3 - adj);
                BackLeft.setPower(0.3 + adj);
                BackRight.setPower(0.3 - adj);
            }
            else if(angles.firstAngle < startAngle || angle - 360 < startAngle  && angle > 179){
                FrontLeft.setPower(0.3 - adj);
                FrontRight.setPower(0.3 + adj);
                BackLeft.setPower(0.3 - adj);
                BackRight.setPower(0.3 + adj);
            }
            else{
                FrontLeft.setPower(0.3);
                FrontRight.setPower(0.3);
                BackLeft.setPower(0.3);
                BackRight.setPower(0.3);
            }
            telemetry.addData("Angle1", String.format(Locale.US, "%.02f", angles.firstAngle));
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            telemetry.update();
        }
        stopNOW();
    }
    
    
    public void correctReverseTime(int milliseconds){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double startAngle = angles.firstAngle;
        long startTime = System.currentTimeMillis();
        while(opModeIsActive() &&  System.currentTimeMillis() < startTime + milliseconds){      
        
            double adj = Math.abs((angles.firstAngle - startAngle)/25.0);
            double angle = angles.firstAngle;
            if(angle > startAngle || angle + 360 > startAngle && angle < -179){
                FrontLeft.setPower(-0.3 + adj);
                FrontRight.setPower(-0.3 - adj);
                BackLeft.setPower(-0.3 + adj);
                BackRight.setPower(-0.3 - adj);
            }
            else if(angles.firstAngle < startAngle || angle - 360 < startAngle  && angle > 179){
                FrontLeft.setPower(-0.3 - adj);
                FrontRight.setPower(-0.3 + adj);
                BackLeft.setPower(-0.3 - adj);
                BackRight.setPower(-0.3 + adj);
            }
            else{
                FrontLeft.setPower(-0.3);
                FrontRight.setPower(-0.3);
                BackLeft.setPower(-0.3);
                BackRight.setPower(-0.3);
            }
            telemetry.addData("Angle1", String.format(Locale.US, "%.02f", angles.firstAngle));
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            telemetry.update();
        }
        stopNOW();
     }
    

}
