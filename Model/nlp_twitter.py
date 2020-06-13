import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import re
 
import nltk
nltk.download('stopwords')
from nltk.corpus import stopwords
from nltk.stem import PorterStemmer

dataset = pd.read_csv('train.csv')

corpus = []
for i in range(0,10000):
    review = re.sub('[^a-zA-Z]',' ', dataset['tweet'][i])
    review = review.lower()
    review = review.split()
    ps = PorterStemmer()
    review = [ps.stem(word) for word in review if not word in set(stopwords.words('english'))]
    review = ' '.join(review)
    corpus.append(review)
    
from sklearn.feature_extraction.text import CountVectorizer
cv = CountVectorizer()
x = cv.fit_transform(corpus).toarray()

y = dataset.iloc[:10000, 1].values

from sklearn.model_selection import train_test_split
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size = 0.20, random_state = 0)

from sklearn.naive_bayes import GaussianNB
classifier1 = GaussianNB()
classifier1.fit(x_train, y_train)

y_pred_NB = classifier1.predict(x_test)

from sklearn.metrics import confusion_matrix, f1_score
cm_NB = confusion_matrix(y_test, y_pred_NB)
F1_Score_NB = f1_score(y_test,y_pred_NB)

from sklearn.tree import DecisionTreeClassifier
classifier2 = DecisionTreeClassifier(criterion = 'entropy', random_state = 0 )
classifier2.fit(x_train, y_train)

y_pred_DT = classifier2.predict(x_test)
cm_DT = confusion_matrix(y_test, y_pred_DT)
F1_Score_DT = f1_score(y_test,y_pred_DT)
 
from sklearn.ensemble import RandomForestClassifier
classifier3 = RandomForestClassifier(n_estimators = 40, criterion = 'entropy', random_state = 0)
classifier3.fit(x_train, y_train)

y_pred_RF = classifier3.predict(x_test)
cm_RF = confusion_matrix(y_test, y_pred_RF)
F1_Score_RF = f1_score(y_test,y_pred_RF)

F1_Score = [F1_Score_RF, F1_Score_DT, F1_Score_NB]
Methods = ['Random_Forest', 'Decision_Trees', 'Naive_Bayes']
F1_Score_pos = np.arange(len(Methods))
plt.bar(F1_Score_pos, F1_Score) 
plt.xticks(F1_Score_pos, Methods)

plt.title("Comparing the F1 Score of each model")
plt.show()

test_example = " " #enter the statement here
my_list = []
review = re.sub('[^a-zA-Z]',' ', test_example)
review = review.lower()
review = review.split()
ps = PorterStemmer()
review = [ps.stem(word) for word in review if not word in set(stopwords.words('english'))]
review = ' '.join(review)
my_list.append(review)
test_example = cv.transform(my_list).toarray()

y_hat = classifier1.predict(test_example)

df = pd.read_csv(" ") #enter the csv file of many statements
corpus1 = []
for i in range(0,3000):
    review = re.sub('[^a-zA-Z]',' ', df['tweet'][i])
    review = review.lower()
    review = review.split()
    ps = PorterStemmer()
    review = [ps.stem(word) for word in review if not word in set(stopwords.words('english'))]
    review = ' '.join(review)
    corpus1.append(review)
    
x_d = cv.transform(corpus1).toarray()
y_d = classifier2.predict(x_d)

