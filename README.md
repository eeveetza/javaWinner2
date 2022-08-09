# Java Implementation of WINNER II Propagation Model

This code repository contains a Java software implementation of [WINNER II](http://www.ero.dk/93F2FC5C-0C4B-4E44-8931-00A5B05A331B) path loss prediction method (Table 4-4).  Note that only outdoor scenarios C1 (Suburban macro-cell), C2 (Urban macro-cell), and D1 (Rural macro-cell) are implemented in this version. The model supports LoS and NLoS propagation conditions as well as the LoS probabilities. 

This version of the code is also implemented in [SEAMCAT](https://seamcat.org). 

The following table describes the structure of the folder `./src/` containing the Java implementation of the Winner II model.

| File/Folder               | Description                                                         |
|----------------------------|---------------------------------------------------------------------|
|`main/Winner2.java`                | Java class implementing WINNER II propagation model        |
|`test/Winner2Test.java`          | Java class implementing validation tests          |


Function call
~~~ 
L = tl_winner2(f, d, h1, h2, env, lostype, variations);
~~~

## Required input arguments of function `tl_winner2`

| Variable          | Type   | Units | Limits       | Description  |
|-------------------|--------|-------|--------------|--------------|
| `f`               | double | GHz   | 2 ≤ `f`≤ 6   | Frequency | 
| `d`               | double | m   | See Note   | 3D direct distance between Tx and Rx stations  |
| `h1`               | double | m   |    | Antenna height of the first terminal |
| `h2`               | double | m   |    | Antenna height of the second terminal |
| `env`      | ClutterEnvironment |    | RURAL, SUBURBAN, URBAN | Environment type |
| `lostype`      | int |     |  | 1 - LoS <br> 2 - NLoS <br> 3 - LoS Probability |
| `variations`      | boolean |     |  | Set to `true` to compute variation in path loss (shadow fading)|

### Note 
Maximum distance: 5 km (10 km for D1 LoS).
<br> Minimum distance:  10 m (30 m for C1) for LoS  and 50 m for NLoS.
<br> Antenna heights must be positive (not smaller than 1 m for C2 LoS)


## Output ##

| Variable   | Type   | Units | Description |
|------------|--------|-------|-------------|
| `L`    | double | dB    | Basic transmission loss |



## References

* [WINNER II](http://www.ero.dk/93F2FC5C-0C4B-4E44-8931-00A5B05A331B)

* [SEAMCAT - Spectrum Engineering Advanced Monte Carlo Analysis Tool](https://seamcat.org)