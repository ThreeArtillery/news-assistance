/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,
  NativeModules,  
  TouchableNativeFeedback
} from 'react-native';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {

  onPressButton = () => {
    console.log("You tapped the button!");
        NativeModules
            .ScriptModule
            .startActivityFromJS("Hello,Tom!");
  }

  onPressShowFloatBallButton = () => {
    console.log("You tapped the button!");
        NativeModules
            .FloatBallModule
            .showFlatBallFromJS("Hello,Tom!");
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native，三个火炮!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit App.js
        </Text>
        <Text style={styles.instructions}>
          {instructions}
        </Text>
        <TouchableNativeFeedback onPress={this.onPressButton}>  
          <Text>跳转到原生页面</Text>  
        </TouchableNativeFeedback>
        <TouchableNativeFeedback onPress={this.onPressShowFloatBallButton}>  
          <Text>打开悬浮框</Text>  
        </TouchableNativeFeedback>  
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
