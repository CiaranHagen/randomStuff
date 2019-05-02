n <- 100;
x <- rnorm( n );

x <- c(2,3,5,7);
x <- numeric(n);


x <- rnorm(100);
X <- matrix(x, nrow=10, ncol=10);

z <- c(1:20);
x <- c(1:20);
m <- x*z;

x <- rnorm(100);
X <- matrix(x, nrow=10, ncol=10);
X <- X*3;
X <- X+2;
a <- nrow(X);
b <- ncol(X);

x <- rnorm(100);
X <- matrix(x, nrow=10, ncol=10);
a <- ncol(X);
if (a>=2)
{
  X <- X[,c(a-1,a)]
}
