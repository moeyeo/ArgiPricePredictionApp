from pandas import read_csv
from pandas import datetime
import pandas as pd
from matplotlib import pyplot
from statsmodels.tsa.arima_model import ARIMA
from sklearn import mean_squared_error
# from sklearn import accuracy_score
import csv
 
def parser(x):
    return datetime.strptime(x, '%Y-%m')
    
series = read_csv('data/bitter_gourd_price.csv',header=0, index_col=0, parse_dates=[0] ,squeeze=True , date_parser=parser)


X = series.values

size = int(len(X) * 0.66)
train, test = X[0:size], X[size:len(X)]
history = [x for x in train]
predictions = list()
for t in range(len(test)):
    model = ARIMA(history, order=(12,1,0))
    model_fit = model.fit(disp=0)
    output = model_fit.forecast()
    yhat = output[0]
    # if(t!=0):
    predictions.append(yhat)
    obs = test[t]
    history.append(obs)
    print('predicted=%f, expected=%f' % (yhat, obs))
predictions.append(yhat)
error = mean_squared_error(test, predictions)

# print('Test MSE: %.3f' % error)
# model_fit.save('model.pkl')
# plot

# series.head()
# pyplot.plot(test)
# pyplot.plot(predictions, color='red')
# pyplot.show()
