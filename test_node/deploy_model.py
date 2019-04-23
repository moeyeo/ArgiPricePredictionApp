from pandas import Series
from statsmodels.tsa.arima_model import ARIMAResults
import numpy
from statsmodels.tsa.arima_model import ARIMA
import sys
from pandas import read_csv
from pandas import datetime
import pandas as pd
from matplotlib import pyplot
from statsmodels.tsa.arima_model import ARIMA
import csv
from pymongo import MongoClient


def parser(x):
    return datetime.strptime(x, '%Y-%m')

def deploy(Name):
        veg = Name
        data = "data/"+Name.lower().replace(" ","_")+"_price.csv"
        print(data)
        series = read_csv(data,header=0, index_col=0, parse_dates=[0] ,squeeze=True , date_parser=parser)

        X = series.values

        history = [x for x in X]
        predictions = []
        test = []
        AvgPrice = history[-1]
        for t in range(13):
                model = ARIMA(history, order=(3,1,0))
                model_fit = model.fit(disp=0)
                output = model_fit.forecast()
                yhat = output[0]
                if(t!=0):
                        predictions.append(yhat[0])
                        history.append(yhat[0])
        # print('predicted=%f' % predictions[t-1])
        print(predictions)
        # sys.stdout.flush()
        bestPrice = predictions[0]
        bestMonth = 0
        for i in range(12):
                if(predictions[i]>bestPrice):
                        bestMonth = i
                        bestPrice = predictions[i]
        print(bestMonth+1)
        print(AvgPrice)
        updateMongo(veg,AvgPrice,bestMonth+1,predictions)

def updateMongo(veg,AvgPrice,bestMonth,predictions):
        client = MongoClient('localhost', 27017)
        db = client.predictapp_mongodb
        db.vegs_mongo.update_one({"result.Name" : veg},
                { "$set":  {"result.$.AvgPrice": AvgPrice}})
        db.vegs.update_one( { "Name" : veg }, {"$set": { "AvgPrice" : AvgPrice}})
        db.vegs.update_one( { "Name" : veg }, {"$set": { "BestPrice" : bestMonth}})
        db.vegs.update_one( { "Name" : veg }, {"$set": { "Predictions" : predictions }})

arr = ["Baby Corn","Bitter Gourd","Cabbage","Cauliflower","Celery","Chinese Cabbage","Choy",
        "Coriander","Cucumber","Dried Roselle","Ginger","Green Lettuce","Kale","Lady Finger Banana",
        "Lime","Potato","Radish","Tomato","Water Spinach","Kinnaree Watermelon","Yardlong Bean","Banana"]
print(len(arr))        
for i in range(len(arr)):
        deploy(arr[i])

# error = mean_squared_error(test, predictions)
# print('Test MSE: %.3f' % error)
# plot

# series.head()
# pyplot.plot(test)
# pyplot.plot(predictions, color='red')
# pyplot.show()
