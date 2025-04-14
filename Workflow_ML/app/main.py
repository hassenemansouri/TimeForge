from flask import Flask, request, jsonify
from flask_cors import CORS
import joblib
from pathlib import Path
import logging

# Initialiser Flask
app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": "http://localhost:4200"}})

# Logger
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# Charger modèle et vectorizer
model = None
vectorizer = None

def load_models():
    global model, vectorizer
    try:
        base_path = Path(__file__).resolve().parent
        model_path = base_path / "models" / "workflow_model.pkl"
        vectorizer_path = base_path / "models" / "vectorizer.pkl"

        if not model_path.exists():
            raise FileNotFoundError(f"Fichier modèle introuvable : {model_path}")
        if not vectorizer_path.exists():
            raise FileNotFoundError(f"Fichier vectorizer introuvable : {vectorizer_path}")

        model = joblib.load(model_path)
        vectorizer = joblib.load(vectorizer_path)
        logger.info("✅ Modèles chargés avec succès")
    except Exception as e:
        logger.error(f"❌ Erreur lors du chargement des modèles : {e}")

load_models()

# Nouvelle version stricte
def get_clean_string_field(data, field_name):
    if field_name not in data:
        raise ValueError(f"Le champ '{field_name}' est requis.")

    value = data[field_name]
    if not isinstance(value, str):
        raise ValueError(f"Le champ '{field_name}' doit être une chaîne de caractères.")

    return value.strip()

@app.route("/home", methods=["GET"])
def home():
    return "🚀 API Flask de prédiction de workflow opérationnelle ! ✅ "

@app.route("/predict", methods=["POST"])
def predict():
    if model is None or vectorizer is None:
        logger.error("❌ Le modèle ou le vectorizer n'est pas chargé")
        return jsonify({"error": "Modèle non chargé"}), 500

    try:
        data = request.get_json()
        if not data:
            raise ValueError("❌ Données JSON manquantes")

        steps = get_clean_string_field(data, "steps")
        combined_text = f"{steps}"
        input_vec = vectorizer.transform([combined_text])
        prediction = model.predict(input_vec)[0]

        logger.info(f"✅ Prédiction réussie : {prediction}")
        return jsonify({"prediction": prediction})

    except ValueError as ve:
        logger.warning(f"⚠️ Erreur de validation : {ve}")
        return jsonify({"error": str(ve)}), 400

    except Exception as e:
        logger.error(f"❌ Erreur pendant la prédiction : {str(e)}")
        return jsonify({"error": "Erreur de prédiction", "details": str(e)}), 500

if __name__ == "__main__":
    app.run(debug=True, port=5000)
