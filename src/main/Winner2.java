package main;

import java.util.Random;



// Winner 2

public class Winner2{

    // Class implementation of Winner 2 propagation model
    //
    // THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
    // EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
    // MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
    // IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
    // OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
    // ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
    // OTHER DEALINGS IN THE SOFTWARE.
    //
    // You may improve, modify, and create derivative works of the software or
    // any portion of the software, and you may copy and distribute such
    // modifications or works. Modified works should carry a notice stating
    // that you changed the software and should note the date and nature of
    // any such change.
    //
    // Please provide appropriate acknowledgments in any copies or
    // derivative works of this software.


    public enum ClutterEnvironment {

        NONE("No clutter"),
        WATER("Water/Sea"),
        URBAN("Urban"),
        URBAN_MICRO("Urban Micro Cell"),
        SUBURBAN("Suburban"),
        DENSE_SUBURBAN("Dense Suburban"),
        RURAL("Rural"),
        DENSE_URBAN("Dense Urban"),
        HIGH_RISE_URBAN("High-rise Urban"),
        RESIDENTIAL("Residential"),
        INDUSTRIAL("Industrial zone"),
        USER_SPECIFIED("User specified");
        private String name;
        ClutterEnvironment(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public double getGaussianDistribution(double mu, double sigma){

        Random x = new Random();

        return x.nextGaussian()*sigma + mu;
    }

    public double getUniformDistribution(double x1, double x2){

        Random x = new Random();

        return x1 + (x2-x1)*x.nextDouble();
    }


    public double tl_winner2(double f, double d, double h1, double h2, ClutterEnvironment env, int lostype, boolean variations) {

        double hbs = h1;
        double hms = h2;

        if (h1 < h2) {
            hbs = h2;
            hms = h1;
        }

        double fmin = 2;
        double fmax = 6;

        double h = 20;
        double W = 20;

        double dmin = 10;
        double dmax = 5000;

        double hbsmin = 0.0;
        double hmsmin = 0.0;

        if (env == ClutterEnvironment.RURAL && (lostype == 1)) {
            dmax = 10000;
        }

        if (env == ClutterEnvironment.RURAL && (lostype == 2 || lostype == 3)) {
            dmin = 50;
        }

        if (env == ClutterEnvironment.SUBURBAN) {
            if (lostype == 1) {
                dmin = 30;
            } else { // lostype == 2 || lostype == 3
                dmin = 50;
            }
        }

        if (env == ClutterEnvironment.URBAN) {
            if (lostype == 1 || lostype == 3) {
                hbsmin = 1;
                hmsmin = 1;
            }
        }


        if (f < fmin || f > fmax) {
            throw new RuntimeException("The chosen model is valid for frequencies in the range [" + Double.toString(fmin) + ", " + Double.toString(fmax) + "] GHz");
        }


        // check the heights
        // categorize mobile and base station


        if (hbs <= hbsmin) {
            throw new RuntimeException("The chosen model is valid for base station heights greater than " + Double.toString(hbsmin) + " m");
        }


        if (hms <= hmsmin) {
            throw new RuntimeException("The chosen model is valid for mobile station heights greater than " + Double.toString(hmsmin) + " m");
        }


        double L = 0.0;
        double StdDev = 0.0;
        double prob = 1; // probability that the path is LOS

        if (lostype == 2) { //NLOS
            prob = 0;
        }

        if (lostype == 3) {
            if (env == ClutterEnvironment.URBAN) {

                prob = Math.min(18.0 / d, 1.0) * (1 - Math.exp(-d / 63.0)) + Math.exp(-d / 63.0);

            } else if (env == ClutterEnvironment.SUBURBAN) {


                prob = Math.exp(-(d) / 200);


            } else {// envtype == 3

                prob = Math.exp(-(d) / 1000.0);


            }
        }

        double[] out;

        if (lostype == 1) { //LOS
            if (env == ClutterEnvironment.URBAN) {// UMa
                out = winner2_UMa(f, d, hbs, hms, true);
                L = out[0];
                StdDev = out[1];

                if (variations) {
                    double std = getGaussianDistribution(0, StdDev);

                    L = L + std;
                }

            } else if (env == ClutterEnvironment.SUBURBAN) { // SMa
                out = winner2_SMa(f, d, hbs, hms, true);
                L = out[0];
                StdDev = out[1];

                if (variations) {
                    double std = getGaussianDistribution(0, StdDev);

                    L = L + std;
                }
            } else if (env == ClutterEnvironment.RURAL) { // RMa
                out = winner2_RMa(f, d, hbs, hms, true);
                L = out[0];
                StdDev = out[1];

                if (variations) {
                    double std = getGaussianDistribution(0, StdDev);

                    L = L + std;
                }

            }

        } else if (lostype == 2) { //NLOS
            if (env == ClutterEnvironment.URBAN) {// UMa
                out = winner2_UMa(f, d, hbs, hms, false);
                L = out[0];
                StdDev = out[1];

                if (variations) {
                    double std = getGaussianDistribution(0, StdDev);

                    L = L + std;
                }

            } else if (env == ClutterEnvironment.SUBURBAN) { // SMa
                out = winner2_SMa(f, d, hbs, hms, false);
                L = out[0];
                StdDev = out[1];

                if (variations) {
                    double std = getGaussianDistribution(0, StdDev);

                    L = L + std;
                }
            } else if (env == ClutterEnvironment.RURAL) { // RMa
                out = winner2_RMa(f, d, hbs, hms, false);
                L = out[0];
                StdDev = out[1];

                if (variations) {
                    double std = getGaussianDistribution(0, StdDev);

                    L = L + std;
                }
            }
        } else { //LOS probabilities
            if (env == ClutterEnvironment.URBAN) {// UMa
                out = winner2_UMa(f, d, hbs, hms, true);
                double L1 = out[0];
                double StdDev1 = out[1];

                out = winner2_UMa(f, d, hbs, hms, false);
                double L2 = out[0];
                double StdDev2 = out[1];

                if (variations) {
                    double std1 = getGaussianDistribution(0, StdDev1);
                    double std2 = getGaussianDistribution(0, StdDev2);
                    L1 = L1 + std1;
                    L2 = L2 + std2;
                }

                // L = prob*L1 + (1-prob)*L2;
                double p_trial = getUniformDistribution(0.0, 1.0);
                if (p_trial < prob) {
                    L = L1; //LOS
                } else {
                    L = L2; //NLOS
                }

            } else if (env == ClutterEnvironment.SUBURBAN) { //SMa
                out = winner2_SMa(f, d, hbs, hms, true);
                double L1 = out[0];
                double StdDev1 = out[1];

                out = winner2_SMa(f, d, hbs, hms, false);
                double L2 = out[0];
                double StdDev2 = out[1];

                if (variations) {
                    double std1 = getGaussianDistribution(0, StdDev1);
                    double std2 = getGaussianDistribution(0, StdDev2);
                    L1 = L1 + std1;
                    L2 = L2 + std2;
                }

                //L = prob*L1 + (1-prob)*L2;
                double p_trial = getUniformDistribution(0.0, 1.0);
                if (p_trial < prob) {
                    L = L1; //LOS
                } else {
                    L = L2; //NLOS
                }

            } else if (env == ClutterEnvironment.RURAL) { //RMa
                out = winner2_RMa(f, d, hbs, hms, true);
                double L1 = out[0];
                double StdDev1 = out[1];

                out = winner2_RMa(f, d, hbs, hms, false);
                double L2 = out[0];
                double StdDev2 = out[1];

                if (variations) {
                    double std1 = getGaussianDistribution(0, StdDev1);
                    double std2 = getGaussianDistribution(0, StdDev2);
                    L1 = L1 + std1;
                    L2 = L2 + std2;
                }


                double p_trial = getUniformDistribution(0.0, 1.0);
                if (p_trial < prob) {
                    L = L1; //LOS
                } else {
                    L = L2; //NLOS
                }

            }
        }


        return L;
    }


    public double[] winner2_UMa(double f, double d, double hbs, double hms, boolean los) {
        //    winner2_UMa computes path loss for urban macro cell
        //   This function computes path loss according to Winner II model for
        //   urban macro cell (C2)
        // Input parameters:
        //     f       -   Frequency (GHz)
        //     d       -   Tx-Rx distance (m) 
        //     hbs     -   base station height (m) 
        //     hms     -   mobile station height (m) 
        //     los     -   flag: true = Line of Sight, false = Non Line of Sight
        // Output parameters:
        //     out     -   an array of output values
        //                 out(1) - path loss
        //                 out(2) - standard deviation


        //  Numbers refer to Report IST-4-027756 WINNER II, D1.1.2 V1.2, WINNER II
        //  Channel Models, Part I Channel Models

        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    06JUN18     Ivica Stevanovic, OFCOM         Initial version

        // check the parameter values and issue warnings

        double sigma = 4.0;
        double L = 0.0;

        if (hbs <= 0 || hms <= 0) {
            throw new RuntimeException("Antenna heights must be larger than 0 m.");
        }

        if (los) {

            // make sure antenna heights are larger than 1 m
            if (hbs <= 1 || hms <= 1) {
                throw new RuntimeException("Antenna heights for Winner II C2 must be larger than 1 m.");
            }
            // compute the effective antenna heights
            double hbs1 = hbs - 1.0;
            double hms1 = hms - 1.0;
            // compute the break point

            double dbp = 4 * (hbs1) * (hms1) * f * 10.0 / 3.0; // computed according to the note 4) in Table 4-4

            if (d < dbp) {
                sigma = 4;
                double A = 26;
                double B = 39;
                double C = 20;

                L = A * Math.log10(d) + B + C * Math.log10(f/5.0);

            } else {
                sigma = 6;

                L = 40.0*Math.log10(d) + 13.47 - 14.0*Math.log10(hbs1)-14.0*Math.log10(hms1) + 6.0*Math.log10(f/5.0);

            }

        } else { // NLOS

            sigma = 8;

            L = (44.9 - 6.55*Math.log10(hbs))*Math.log10(d) + 34.46 + 5.83*Math.log10(hbs) + 23.0*Math.log10(f/5.0);
        }

        double[] out = new double[2];

        out[0] = L;
        out[1] = sigma;

        return out;
    }

    public double[] winner2_SMa( double f, double d, double hbs, double hms,  boolean los) {
        //    winner2_SMa computes path loss for suburban macro cell
        //   This function computes path loss according to Winner II model for
        //   suburban macro cell (C1)
        // Input parameters:
        //     f       -   Frequency (GHz)
        //     d       -   Tx-Rx distance (m) 
        //     hbs     -   base station height (m) 
        //     hms     -   mobile station height (m) 
        //     los     -   flag: true = Line of Sight, false = Non Line of Sight
        // Output parameters:
        //     out     -   an array of output values
        //                 out(1) - path loss
        //                 out(2) - standard deviation


        // Numbers refer to Report IST-4-027756 WINNER II, D1.1.2 V1.2, WINNER II
        //  Channel Models, Part I Channel Models

        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    06JUN18     Ivica Stevanovic, OFCOM         Initial version

        // check the parameter values and issue warnings

        double sigma = 4.0;
        double L = 0.0;

        if (hbs <= 0 || hms <= 0) {
            throw new RuntimeException("Antenna heights must be larger than 0 m.");
        }

        if (los) {

            // compute the break point

            double dbp = 4 * (hbs) * (hms) * f * 10.0 / 3.0; // computed according to the note 6) in Table 4-4

            if (d < dbp) {

                sigma = 4;
                double A = 23.8;
                double B = 41.2;
                double C = 20;

                L = A*Math.log10(d) + B + C*Math.log10(f/5.0);

            } else {

                sigma = 6;

                L = 40.0*Math.log10(d) + 11.65 - 16.2*Math.log10(hbs)-16.2*Math.log10(hms) + 3.8*Math.log10(f/5.0);
            }


        } else { // NLOS

            sigma = 8;

            L = (44.9 - 6.55*Math.log10(hbs))*Math.log10(d) + 31.46 + 5.83*Math.log10(hbs) + 23.0*Math.log10(f/5.0);
        }

        double[] out = new double[2];

        out[0] = L;
        out[1] = sigma;

        return out;
    }


    public double[] winner2_RMa( double f, double d, double hbs, double hms, boolean los) {
        //    winner2_RMa computes path loss for rural macro cell
        //   This function computes path loss according to Winner II model for
        //   rural macro cell (D1)
        // Input parameters:
        //     f       -   Frequency (GHz)
        //     d       -   Tx-Rx distance (m) 
        //     hbs     -   base station height (m) 
        //     hms     -   mobile station height (m) 
        //     los     -   flag: true = Line of Sight, false = Non Line of Sight
        // Output parameters:
        //     out     -   an array of output values
        //                 out(1) - path loss
        //                 out(2) - standard deviation


        //  Numbers refer to Report IST-4-027756 WINNER II, D1.1.2 V1.2, WINNER II
        // Channel Models, Part I Channel Models1

        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    06JUN18     Ivica Stevanovic, OFCOM         Initial version
        //     v1    29OCT18     Max Friedrich, BNetzA           Bug fixed: New Constant C=20

        // check the parameter values and issue warnings

        double sigma = 4.0;
        double L = 0.0;

        if (hbs <= 0 || hms <= 0) {
            throw new RuntimeException("Antenna heights must be larger than 0 m.");
        }

        if (los) {

            // compute the break point

            double dbp = 4 * (hbs) * (hms) * f * 10.0 / 3.0; // computed according to the note 6) in Table 4-4

            if (d < dbp) {

                sigma = 4;
                double A = 21.5;
                double B = 44.2;
                //outdated version with Factor C=22.0
                //double C = 22.0;
                //Version amended by BNetzA according IST-4-027756 WINNER II description using Factor "C=20" instead of "C=22":
                double C = 20.0;

                L = A*Math.log10(d) + B + C*Math.log10(f/5.0);

            } else {

                sigma = 6;

                L = 40.0*Math.log10(d) + 10.5 - 18.5*Math.log10(hbs)-18.5*Math.log10(hms) + 1.5*Math.log10(f/5.0);
            }


        } else { // NLOS

            sigma = 8;

            L = 25.1*Math.log10(d) + 55.4 - 0.13*(hbs-25)*Math.log10(d/100) - 0.9*(hms-1.5) + 21.3*Math.log10(f/5.0);

        }

        double[] out = new double[2];

        out[0] = L;
        out[1] = sigma;

        return out;
    }
}
