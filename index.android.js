/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

class AwesomeProject extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, rytertyrt
        </Text>
        <Text style={styles.instructions2}>
            tyryry sdsd
        </Text>
        <Text style ={styles.instructions3}>hello {'<3'}
        </Text>
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
    instructions2: {
        textAlign: 'center',
        color: 'blue',
        fontWeight: 'bold',
        marginBottom: 5,
    },
    instructions3: {
        fontSize: 15,
        textAlign: 'center',
        color: 'red',
        fontWeight: 'bold',
        marginBottom: 5,
    },
});

AppRegistry.registerComponent('AwesomeProject', () => AwesomeProject);
