setwd("C:/Users/potatochipse/Desktop/SFSU Classes/MATH 324/Project9/")
statistics <- read.csv("WarThunderStatistics9.csv")
NetSLData <- statistics$Net.SL.Earnings
hist(NetSLData, main="Net Silver Lion Earnings Earned In War Thunder Matches", xlab="Net Silver Lion Earnings", breaks=10)

# Set sample size
n = 10

# Create a storage vector
sampleAvgs_10 = numeric(1000)

# Create 1000 sample averages in a loop
for (i in 1:1000) {
  sampleAvgs_10[i] <- mean(sample(NetSLData, size=5, replace=TRUE))
}

hist(sampleAvgs_10, 
     main="Net Silver Lion Earnings Earned In War Thunder Matches", 
     xlab="Net Silver Lion Earnings", 
     breaks=20)

mean(sampleAvgs_10)
var(sampleAvgs_10)

