package test;

import main.Winner2;

import org.junit.Before;
import org.junit.Test;


public class Winner2Test {
    // the results are compared to the MATLAB implementation of Recommendation Winner II
    // the test is passed when the results for transmission loss are within 0.01 dB of difference
    // different location percentages, frequencies, distances, and environment categories are tested
    //     Rev   Date        Author                          Description
    //     ------------------------------------------------------------------------------
    //     v0    07JUN18     Ivica Stevanovic, OFCOM         Introduced tests for WInner II


    TestUtil util;

    @Before
    public void setup() {

        util = new TestUtil(0.01);
    }


    @Test
    public void test1() {
        Winner2 calculator = new Winner2();
        double[] f = new double[5];
        double[] d = new double[5];
        int[] los = new int[2];
        double hbs, hms;
        f[0] = 2.000000;
        f[1] = 3.000000;
        f[2] = 4.000000;
        f[3] = 5.000000;
        f[4] = 6.000000;
        d[0] = 10.000000;
        d[1] = 100.000000;
        d[2] = 1000.000000;
        d[3] = 2000.000000;
        d[4] = 5000.000000;
        los[0] = 1;
        los[1] = 2;
        hbs = 35.000000;
        hms = 1.500000;
        double[] expectedResult = new double[50];
        expectedResult[0] = 57.041200;
        expectedResult[1] = 69.095651;
        expectedResult[2] = 83.041200;
        expectedResult[3] = 103.882005;
        expectedResult[4] = 113.856075;
        expectedResult[5] = 138.668359;
        expectedResult[6] = 125.897275;
        expectedResult[7] = 149.140096;
        expectedResult[8] = 141.814875;
        expectedResult[9] = 162.982978;
        expectedResult[10] = 60.563025;
        expectedResult[11] = 73.145750;
        expectedResult[12] = 86.563025;
        expectedResult[13] = 107.932104;
        expectedResult[14] = 114.912623;
        expectedResult[15] = 142.718458;
        expectedResult[16] = 126.953822;
        expectedResult[17] = 153.190194;
        expectedResult[18] = 142.871423;
        expectedResult[19] = 167.033077;
        expectedResult[20] = 63.061800;
        expectedResult[21] = 76.019341;
        expectedResult[22] = 89.061800;
        expectedResult[23] = 110.805695;
        expectedResult[24] = 115.662255;
        expectedResult[25] = 145.592049;
        expectedResult[26] = 127.703455;
        expectedResult[27] = 156.063785;
        expectedResult[28] = 143.621055;
        expectedResult[29] = 169.906668;
        expectedResult[30] = 65.000000;
        expectedResult[31] = 78.248271;
        expectedResult[32] = 91.000000;
        expectedResult[33] = 113.034625;
        expectedResult[34] = 117.000000;
        expectedResult[35] = 147.820980;
        expectedResult[36] = 128.284915;
        expectedResult[37] = 158.292716;
        expectedResult[38] = 144.202515;
        expectedResult[39] = 172.135598;
        expectedResult[40] = 66.583625;
        expectedResult[41] = 80.069440;
        expectedResult[42] = 92.583625;
        expectedResult[43] = 114.855794;
        expectedResult[44] = 118.583625;
        expectedResult[45] = 149.642148;
        expectedResult[46] = 128.760002;
        expectedResult[47] = 160.113884;
        expectedResult[48] = 144.677603;
        expectedResult[49] = 173.956767;
        int count = 0;
        for (int fi = 0; fi < 5; fi++) {
            for (int di = 0; di < 5; di++) {
                for (int li = 0; li < 2; li++) {
                    //double[] out = calculator.winner2_UMa(f[fi], d[di], hbs, hms, los[li]);
                    //double result = out[0];
                    double result = calculator.tl_winner2(f[fi], d[di], hbs, hms, Winner2.ClutterEnvironment.URBAN, los[li], false);

                    util.assertDoubleEquals(expectedResult[count], result);
                    count = count + 1;
                }
            }
        }
    }

    @Test
    public void test2() {
        Winner2 calculator = new Winner2();
        double[] f = new double[5];
        double[] d = new double[5];
        int[] los = new int[2];
        double hbs, hms;
        f[0] = 2.000000;
        f[1] = 3.000000;
        f[2] = 4.000000;
        f[3] = 5.000000;
        f[4] = 6.000000;
        d[0] = 10.000000;
        d[1] = 100.000000;
        d[2] = 1000.000000;
        d[3] = 2000.000000;
        d[4] = 5000.000000;
        los[0] = 1;
        los[1] = 2;
        hbs = 35.000000;
        hms = 1.500000;
        double[] expectedResult = new double[50];
        expectedResult[0] = 57.041200;
        expectedResult[1] = 66.095651;
        expectedResult[2] = 80.841200;
        expectedResult[3] = 100.882005;
        expectedResult[4] = 104.641200;
        expectedResult[5] = 135.668359;
        expectedResult[6] = 114.312447;
        expectedResult[7] = 146.140096;
        expectedResult[8] = 130.230047;
        expectedResult[9] = 159.982978;
        expectedResult[10] = 60.563025;
        expectedResult[11] = 70.145750;
        expectedResult[12] = 84.363025;
        expectedResult[13] = 104.932104;
        expectedResult[14] = 108.163025;
        expectedResult[15] = 139.718458;
        expectedResult[16] = 115.327539;
        expectedResult[17] = 150.190194;
        expectedResult[18] = 130.899194;
        expectedResult[19] = 164.033077;
        expectedResult[20] = 63.061800;
        expectedResult[21] = 73.019341;
        expectedResult[22] = 86.861800;
        expectedResult[23] = 107.805695;
        expectedResult[24] = 110.661800;
        expectedResult[25] = 142.592049;
        expectedResult[26] = 117.826314;
        expectedResult[27] = 153.063785;
        expectedResult[28] = 131.373961;
        expectedResult[29] = 166.906668;
        expectedResult[30] = 65.000000;
        expectedResult[31] = 75.248271;
        expectedResult[32] = 88.800000;
        expectedResult[33] = 110.034625;
        expectedResult[34] = 112.600000;
        expectedResult[35] = 144.820980;
        expectedResult[36] = 119.764514;
        expectedResult[37] = 155.292716;
        expectedResult[38] = 131.742219;
        expectedResult[39] = 169.135598;
        expectedResult[40] = 66.583625;
        expectedResult[41] = 77.069440;
        expectedResult[42] = 90.383625;
        expectedResult[43] = 111.855794;
        expectedResult[44] = 114.183625;
        expectedResult[45] = 146.642148;
        expectedResult[46] = 121.348139;
        expectedResult[47] = 157.113884;
        expectedResult[48] = 132.043108;
        expectedResult[49] = 170.956767;
        int count = 0;
        for (int fi = 0; fi < 5; fi++) {
            for (int di = 0; di < 5; di++) {
                for (int li = 0; li < 2; li++) {
                    //double[] out = calculator.winner2_SMa(f[fi], d[di], hbs, hms, los[li]);
                    //double result = out[0];
                    double result = calculator.tl_winner2(f[fi], d[di], hbs, hms, Winner2.ClutterEnvironment.SUBURBAN, los[li], false);
                    util.assertDoubleEquals(expectedResult[count], result);
                    count = count + 1;
                }
            }
        }
    }

    @Test
    public void test3() {
        Winner2 calculator = new Winner2();
        double[] f = new double[5];
        double[] d = new double[5];
        int[] los = new int[2];
        double hbs, hms;
        f[0] = 2.000000;
        f[1] = 3.000000;
        f[2] = 4.000000;
        f[3] = 5.000000;
        f[4] = 6.000000;
        d[0] = 10.000000;
        d[1] = 100.000000;
        d[2] = 1000.000000;
        d[3] = 2000.000000;
        d[4] = 5000.000000;
        los[0] = 1;
        los[1] = 2;
        hbs = 35.000000;
        hms = 1.500000;
        double[] expectedResult = new double[50];
        expectedResult[0] = 57.741200;
        expectedResult[1] = 73.323878;
        expectedResult[2] = 79.241200;
        expectedResult[3] = 97.123878;
        expectedResult[4] = 100.741200;
        expectedResult[5] = 120.923878;
        expectedResult[6] = 110.121343;
        expectedResult[7] = 128.088392;
        expectedResult[8] = 126.038943;
        expectedResult[9] = 137.559364;
        expectedResult[10] = 61.263025;
        expectedResult[11] = 77.074622;
        expectedResult[12] = 82.763025;
        expectedResult[13] = 100.874622;
        expectedResult[14] = 104.263025;
        expectedResult[15] = 124.674622;
        expectedResult[16] = 110.735170;
        expectedResult[17] = 131.839136;
        expectedResult[18] = 126.303080;
        expectedResult[19] = 141.310108;
        expectedResult[20] = 63.761800;
        expectedResult[21] = 79.735817;
        expectedResult[22] = 85.261800;
        expectedResult[23] = 103.535817;
        expectedResult[24] = 106.761800;
        expectedResult[25] = 127.335817;
        expectedResult[26] = 113.233945;
        expectedResult[27] = 134.500331;
        expectedResult[28] = 126.490488;
        expectedResult[29] = 143.971303;
        expectedResult[30] = 65.700000;
        expectedResult[31] = 81.800000;
        expectedResult[32] = 87.200000;
        expectedResult[33] = 105.600000;
        expectedResult[34] = 108.700000;
        expectedResult[35] = 129.400000;
        expectedResult[36] = 115.172145;
        expectedResult[37] = 136.564514;
        expectedResult[38] = 126.635853;
        expectedResult[39] = 146.035486;
        expectedResult[40] = 67.283625;
        expectedResult[41] = 83.486561;
        expectedResult[42] = 88.783625;
        expectedResult[43] = 107.286561;
        expectedResult[44] = 110.283625;
        expectedResult[45] = 131.086561;
        expectedResult[46] = 116.755770;
        expectedResult[47] = 138.251074;
        expectedResult[48] = 126.754625;
        expectedResult[49] = 147.722047;
        int count = 0;
        for (int fi = 0; fi < 5; fi++) {
            for (int di = 0; di < 5; di++) {
                for (int li = 0; li < 2; li++) {
                    //double[] out = calculator.winner2_RMa(f[fi], d[di], hbs, hms, los[li]);
                    //double result = out[0];
                    double result = calculator.tl_winner2(f[fi], d[di], hbs, hms, Winner2.ClutterEnvironment.RURAL, los[li], false);
                    util.assertDoubleEquals(expectedResult[count], result);
                    count = count + 1;
                }
            }
        }
    }



}
