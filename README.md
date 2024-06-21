# BANGKIT FINAL PROJECT
# Shroomscan
Work on this model project using Google Colab (limited use of our hardware requirements on a Laptop)
Link to Colab:
https://colab.research.google.com/drive/1-44mK2lAB-ZgctTWVelFoS0SulvyvJx9?usp=sharing
# 1. Load Datasets
   - Load datasets from our hosted dataset to Google Drive. 
     Here's the link:
     https://drive.google.com/uc?id=1b9WJpb6cnDhfDuVUqZwBtmkFe6XtU2r4
# 2. Pre-processing Datasets
   - We directly separate the dataset manually by deleting the type of each class in the dataset because in the dataset image there is a different image from the mushroom image.
   - Spliting datasets into:
     - 80% of train dataset
     - 20% of validation dataset
     - 5% of test data
   - Resizing the datasets into 299x299
# 3. Training the model
   - Using transfer learning InceptionV3 to make a model accuracy better
   - Using categorical_crossentropy as loss
   - Using Adam(learning_rate=0.0001) as optimizer
   - Added more layer to make model accuracy more better:
      - Added `GlobalAveragePooling2D` layer
      - Added `Dense(256, activation='relu')` layer
      - Added `BatchNormalization` layer
      - Added `Dropout(units=0.4)` layer
      - Added 2 output layer :
        `mushroom_class_output = Dense(len(edible_classes) + len(non_edible_classes), activation='softmax', name='mushroom_class_output')` for mushroom name class and `edibility_output = Dense(2, activation='softmax', name='edibility_output')` for mushroom type names
   - Training with 25 epochs
   - From the result, got:
       - `loss: 23%`
       - `accuracy: 99%`
       - `val_loss: 1%`
       - `val_accuracy: 99%`
# 4. Saved the Model to Google Drive
   - Saved the model (*h5 format) to Google Drive:
     
