data <- read.csv("input.csv")

#Selecting columns
age <- data[1:7,1:1]
circum <- data[1:7,2:2]

plot(age, circum, xlab = "age", ylab = "circumference")

data <- data.frame(age, circum)
model = lm(circum ~ age, data)
summary(model)

abline(model, cex=1)

new_data <- data.frame(age = 700)
result <- predict(model, new_data)
print(result)
