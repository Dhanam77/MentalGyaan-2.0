from flask import Flask
from flask import jsonify, request
app = Flask(__name__)


@app.route("/sentiment", methods = ['POST'])
def hello():

    json = request.json

    if json == None:
        return jsonify("null")

    
    for x in json:
        s = json[x];
        if s == "You are beautiful":
            s = "Happy"
        elif s == "I am shit":
            s = "Sad"


    y = {"sentiment": s}

    return jsonify(y)

if __name__ == '__main__':
    app.run(debug=True)