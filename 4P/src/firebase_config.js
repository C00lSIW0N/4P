// Import the functions you need from the SDKs you need
// import { initializeApp } from "firebase/app";
// import { getAnalytics } from "firebase/analytics";
// import { getFirestore } from "firebase/firestore";
// import firebase from "firebase";
import firebase from 'firebase/compat/app';
// import { initializeApp } from "firebase/app";
import 'firebase/compat/firestore';
// v9 compat packages are API compatible with v8 code

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCChEnJNliHdnXkG9Ni8N8267TmvB4ONYU",
  authDomain: "capstone-cdb49.firebaseapp.com",
  projectId: "capstone-cdb49",
  storageBucket: "capstone-cdb49.appspot.com",
  messagingSenderId: "588897360181",
  appId: "1:588897360181:web:8e5533bacfa8de931d147c",
  measurementId: "G-B96Y8P5V5C"
};

// Initialize Firebase
// const app = initializeApp(firebaseConfig);
// const analytics = getAnalytics(app);
// const firestore = firebase.firestore();


const firebaseApp = firebase.initializeApp(firebaseConfig);
export const firestore = firebaseApp.firestore();
