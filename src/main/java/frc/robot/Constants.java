package frc.robot;

import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import frc.lib.util.CANSparkMaxUtil.Usage;

public final class Constants {
    
    /* Used for Constants Used Once On Initialization of Robot or Subsystems */
    public final static class Setup {

        /* Swerve Module Ids and Constants */
        public static final int[] moduleIDs = new int[] {0, 1, 2, 3};
        public static final int[] driveMotors = new int[] {1, 3, 5, 7};
        public static final int[] angleMotors = new int[] {2, 4, 6, 8};
        public static final int[] moduleCancoders = new int[] {9, 10, 11, 12};
        public static final double[] angleOffsets = new double[] {0, 0, 0, 0};
        public static final double[] xposition = new double[] {45, 45, -45, -45};

        /* Swerve Motor Inverts */
        public static final boolean driveInvert = false;
        public static final boolean angleInvert = false; //Set false for MK4 modules

        /* Pigeon Id */
        public static final int pigeonID = 13;
    
    }

    public final static class Swerve {
        public static final double stickDeadband = 0.07;

        /* Drivetrain Calculation Constants */
        /* Input these units from center of swerve modules */
        public static final double trackWidth = Units.inchesToMeters(21);
        public static final double trackLength = Units.inchesToMeters(21);

        /* Input Current Wheel Diameter, Can Change Due To Amount Of Wear */
        public static final double wheelDiameter = 100.0 / 1000.0; // mm to m
        public static final double wheelCircimference = wheelDiameter * Math.PI;

        /* Gyro Direction Toggle */
        public static final boolean invertGyro = true; // Always ensure Gyro is CCW+ CW- (Clockwise is increasing rotation values)

        /* Cancoder Invert */
        public static final boolean canCoderInvert = true;

        /* Speed Settings */
        public static final double maxSpeed = 5.00; // meters per second
        public static final double maxAngularVelocity = 4.25; // radians per second

        /* Mk4i Module Gear Ratios */
        public static final double driveGearRatio = (6.75 / 1.0); // 6.75:1
        public static final double angleGearRatio = (12.8 / 1.0); // 12.8:1
    

        /* Swerve Module Positions (Currently in solid rectangle context) */
        public static final Translation2d[] modulePositions = new Translation2d[] {
            new Translation2d(trackLength / 2.0, trackWidth / 2.0),
            new Translation2d(trackLength / 2.0, -trackWidth / 2.0),
            new Translation2d(-trackLength / 2.0, trackWidth / 2.0),
            new Translation2d(-trackLength / 2.0, -trackWidth / 2.0)
        };

        /* Swerve Kinematics */
        public static final SwerveDriveKinematics swerveKinematics =
        new SwerveDriveKinematics(
            modulePositions[0],
            modulePositions[1],
            modulePositions[2],
            modulePositions[3]
        );

        /* Drive Motor Characterization Values */
        /* {Static, Velocity, Acceleration} */    
        public static final double[] driveMotorsSVA = new double[] {0.3, 2.55, 0.27};

        public static final HolonomicPathFollowerConfig pathFollowerConfig = new HolonomicPathFollowerConfig(
            new PIDConstants(5.0, 0, 0), // Translation constants 
            new PIDConstants(5.0, 0, 0), // Rotation constants 
            maxSpeed, 
            modulePositions[0].getNorm(), // Drive base radius (distance from center to furthest module) 
            new ReplanningConfig()
        );
    }

    public static final class AutoConstants {
        
        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;
    
    }

    public final static class Electical {

        /* Base 12 Volt System */
        public static final double voltageComp = 12.0;

        /* Swerve Electrical Limits */
        public static final int driveCurrentLim = 80;
        public static final int angleCurrentLim = 20;

    }

    public final static class PID {

        /* Format {P, I, D, FF} */

        /* Swerve PIDs */
        public static final double[] drivePID = new double[] {0.3, 0.0, 0.0, 0.0};
        public static final double[] anglePID = new double[] {0.01, 0.0, 0.0, 0.0};

    }

    public final static class ConversionFactors {
        /* All numbers in 1 output to required input, or one wheel spin to motor spin */

        /* Swerve Drive Conversions */
        public static final double driveConversionPositionFactor = Swerve.wheelCircimference / Swerve.driveGearRatio;
        public static final double driveConversionVelocityFactor = driveConversionPositionFactor / 60 ; //rpm to rps
        
        public static final double angleConversionPositionFactor = 360.0 / Swerve.angleGearRatio;
        public static final double angleConversionVelocityFactor = angleConversionPositionFactor / 60 ; //rpm to rps

    }

    public final static class IdleModes {
        
        /* Swerve Idles */
        public static final IdleMode driveIdle = IdleMode.kBrake;
        public static final IdleMode angleIdle = IdleMode.kBrake;

    }

    public final static class Usages {

        /* Swerve Usages */
        public static final Usage driveUsage = Usage.kAll;
        public static final Usage angleUsage = Usage.kPositionOnly;

    }

}
