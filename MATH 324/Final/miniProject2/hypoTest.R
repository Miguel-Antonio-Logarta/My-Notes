setwd("C:/Users/potatochipse/Desktop/SFSU Classes/MATH 324/Final/miniProject2")

melee_statistics <- read.csv("meleeWinRates.csv")
ranged_statistics <- read.csv("rangedWinRates.csv")
sample_size <- 50

# melee_sample <- sample(melee_statistics$winRate, sample_size, replace = FALSE)
# ranged_sample <- sample(ranged_statistics$winRate, sample_size, replace = FALSE)

# melee_sample
# ranged_sample

# Samples I got
melee_sample <- c(0.504, 0.503, 0.519, 0.511, 0.527, 0.493, 0.500, 0.484, 0.496, 0.489, 0.507, 0.514, 0.523, 0.523, 0.519, 0.473, 0.496, 0.518, 0.510,
                  0.486, 0.515, 0.500, 0.506, 0.493, 0.535, 0.489, 0.519, 0.512, 0.495, 0.493, 0.474, 0.529, 0.511, 0.515, 0.520, 0.537, 0.503, 0.512,
                  0.508, 0.503, 0.530, 0.525, 0.506, 0.503, 0.512, 0.514, 0.522, 0.516, 0.515, 0.507)
ranged_sample <- c(0.512, 0.497, 0.480, 0.508, 0.506, 0.510, 0.498, 0.511, 0.514, 0.528, 0.500, 0.510, 0.523, 0.509, 0.523, 0.479, 0.507, 0.510, 0.505,
                   0.498, 0.512, 0.508, 0.506, 0.489, 0.498, 0.491, 0.484, 0.515, 0.521, 0.517, 0.504, 0.494, 0.478, 0.498, 0.523, 0.471, 0.512, 0.533,
                   0.489, 0.490, 0.492, 0.508, 0.533, 0.503, 0.502, 0.489, 0.493, 0.509, 0.525, 0.513)

xbar1 <- mean(melee_sample)
xbar2 <- mean(ranged_sample)
sd1 <- sd(melee_sample)
sd2 <- sd(ranged_sample)

se1 <- sd1 / sqrt(sample_size)
se2 <- sd2 / sqrt(sample_size)

se1
se2

tstat <- ((xbar1 - xbar2) - 0)/(sqrt(se1^2+se2^2))

tstat

df <- ((se1^2 + se2^2)^2)/((se1^4)/(sample_size - 1) + (se2^4)/(sample_size - 1))
df

pval <- 1 - pt(tstat, df=df)
pval
