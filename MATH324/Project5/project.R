setwd("C:/Users/potatochipse/Desktop/SFSU Classes/MATH 324/Project5")
statistics <- read.csv("WarThunderStatistics5.csv")
calculations <- read.csv("calculations.csv", header=FALSE)
calculations
statistics
NetEarnings <- statistics$Net.SL.Earnings
totalMatches <- length(NetEarnings)
totalMatches
hist(NetEarnings, main="Net Silver Lion Earnings Earned In War Thunder Matches", xlab="Net Silver Lion Earnings", breaks=10)
summary(NetEarnings)
table(NetEarnings)

SL<-NetEarnings[NetEarnings >= 35000 & NetEarnings < 40000]
length(SL)

