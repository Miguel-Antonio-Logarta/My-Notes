setwd("C:/Users/potatochipse/Desktop/SFSU Classes/MATH 324/Project10/")
statistics <- read.csv("WarThunderData.csv")
net_sl_data <- statistics$Net.SL.Earnings

# Getting the sample
sample_size <- 40;
# sl_sample <- sample(net_sl_data, 40, replace = FALSE)

# Sample I got
sl_sample <- c(-4738, 11395, -312, 5308, 13398, 14940, -2648, 30206, -2202, 4457, 20913, 670, 9189, 320, -4220, 
               32197, 7464, -4138, -8947, 3147, 555, -2397, -3049, 18262, 25225, -51, 1529, 9796, 2986, 5843, 
               8419, 14473, -4007, 10248, 7953, 7907, 17471, 13375, 38344, -4355);

hist(sl_sample, main="Net Silver Lion Earnings Earned In War Thunder Matches", xlab="Net Silver Lion Earnings", breaks=10)

# x_bar <- mean(sl_sample)
#n <- sample_size
# s <-  sd(sl_sample) 




x_bar <- 4.770238
n <- 42
df <- n - 1
s <- 0.108459
se <- s / sqrt(n)
tval <- qt(0.005, df, lower.tail=FALSE)
me <- tval*se
low_x = x_bar - me
high_x = x_bar + me

x_bar
n
df
s
se
tval
me
low_x
high_x

