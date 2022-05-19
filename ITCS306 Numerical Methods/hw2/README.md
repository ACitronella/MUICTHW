# Explaination of hw2

1. (a) A program that can take a square root of a number using Taylor Series expansion method. Stop the iteration of the program using stopping criterion. Test with the square root of the number 58.25. 

   (b) A program that can take a cube root of a number using Taylor Series expansion method.
Stop the iteration of the program using stopping criterion. 
Test with the square root of the number 58.25.

    ```
    python hw1.py mode n [-sc sc] [-v]
    ```

    where

    - mode is select mode of square root or cube root
    - n is number which will calcutate sqrt(n) or crt(n)
    - sc is stopping criterion. Default set to 5E-3  
    - -v is for printing explaination

2. A program that can evaluate the value of natural log of 11025 using Taylor Series expansion method.Given that you can find the value of e by setting e = limit (1+(1/n))^n as n approaches infinity.
Stop the iteration of the program using stopping criterion.  Test your result with a calculator.  
(Hint: you cannot apply natural log (1+x) formula directly since the formula requires |x| < 1 - refer to the radius of convergence topic I mentioned in class. Instead, you can split the number 11025 into two numbers a and b where a * b = 11025 and make sure a is exp(k) and |b| < 1. Then apply basic log property: log(a * b) = log(a) + log(b) and log(exp(k)) = k)

    ```
    python hw2.py n [-sc sc] [-v]
    ```

    where

    - n is number which will calcutate ln(n) (natural logarithmic)
    - sc is stopping criterion. Default set to 5E-3
    - -v is for printing explaination

3. Find the n-term approximation of a function (T_n(x)) of sin(x) where n = 2, 4, 6, 8, 10.  
Plot T_n(x) against sin(x) in the way similar to the figure in slide 34.

    ```
    python hw3.py
    ```

Dependecies
- python 3.9.6
- numpy
- matplotlib 

[Video explaination link](https://studentmahidolac-my.sharepoint.com/:v:/g/personal/phuriwat_ang_student_mahidol_ac_th/EcsGv5rBEbdEn4_dqYfyvZgBiAuP1tkbbz3ocK2YO-_b5g?e=iYA9Xm)