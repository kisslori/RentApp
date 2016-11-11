import React, {Component} from 'react';
import {
    AppRegistry,
    Alert,
    StyleSheet,
    Text,
    View,
    Navigator,
    ListView,
    TextInput,
    StatusBar,
    TouchableHighLight,
    TouchableNativeFeedback,
    TouchableOpacity
} from 'react-native';

import Communications from 'react-native-communications';
import Button from 'react-native-button';

var apartments = [
    {"title": "App1", "address": "Main street", "rooms": "21"},
    {"title": "Ap2", "address": "cluj", "rooms": "31"},
    {"title": "House", "address": " dej", "rooms": "5"},
    // {"title": "MoLi", "address": "Leo", "rooms": "2"},
    // {"title": "Hello", "address": "world", "rooms": "!"},
    // {"title": "For rent", "address": "china", "rooms": "2"},
];


class Apartment extends Component {
    constructor(props) {
        super(props);
        this.state = {title: '',address : '', rooms: ''};


    }


    getName() {      return this.state.title    }

    getArtist() {        return this.state.address    }

    getLikes() {        return this.state.rooms    }

    setTitle(title) {        this.state.title = title    }

    setAddress(address) {        this.state.address = address    }

    setRooms(rooms) {        this.state.rooms = rooms    }



    render() {
        return (
            < View >
                < Text > title    :    {this.props.title}
                </Text >
            < / View >
        );
    }
}

/**
 * Some stuff that makes transitions look better
 */

class AwesomeProject extends Component {
    constructor(props) {
        super(props);

        this.state = {
            title : '' ,
            address: '',
            rooms: '',
            dataSource: new ListView.DataSource({
                rowHasChanged: (row1, row2) => row1 !== row2,
            }),
            loaded: false,
        };

    }

    componentDidMount(){
        this.setState({
            dataSource: this.state.dataSource.cloneWithRows(apartments),
            loaded: true,
        });
    }

    _addBtn(){
        apartments.push({ "title": this.state.title, "address": this.state.address, "rooms" : this.state.rooms});
        Alert.alert("Done","Apartment added!");
        this.setState({
            dataSource: this.state.dataSource.cloneWithRows(apartments),
            loaded: true,
        });
    }


    _emailBtn() {
        var apartmentString = apartments.map(function(item) {
            return "\nTitle: " + item['title'] + "\nAddress: " + item['address'] + "\nRooms: " + item['rooms'] +  "\n";
        });

        Communications.email(["kiss.lory@yahoo.com"],"","","Sent from react-native",apartmentString.toString());
    }


    _navigate(apartment){
        this.props.navigator.push({
            name: 'EditDetails',

            passProps: {
                apartment : apartment
            }
        })

    }

    renderApartments(apartments) {
        return (
            <TouchableOpacity
        onPress={ () => this._navigate(apartments)}>
    <View
        style={styles.viewDetails}>
    <Text>{apartments.title}</Text>
        <Text>{apartments.address}</Text>
        <Text>{apartments.rooms}</Text>

        </View>
        </TouchableOpacity>
    );
    }



    render(){
        return (

        <View  style={{backgroundColor: 'white'}}>

        <Text style={styles.header}> Welcome to Apartment Rental App</Text>

            <TextInput
                style= {styles.input}
                onChangeText={(text) => this.setState({title : text})}
                placeholder="Title "
                value = {this.state.title}/>

        <TextInput
            style={styles.input}
            onChangeText={(text) => this.setState({address : text})}
            placeholder="Address "
            value = {this.state.address}/>
        <TextInput
            style={styles.input}
            onChangeText={(text) => this.setState({rooms : text})}
            placeholder="Number of rooms"
            value = {this.state.rooms}/>





<Button
    containerStyle={{padding:8, height:50, overflow:'hidden', borderRadius:6, backgroundColor: '#1565C0', marginBottom: 4}}
    style={{fontSize: 20, color: 'white'}}
    styleDisabled={{color: '#D50000'}}
    onPress={() => this._addBtn()}>Add</Button>

        <ListView
        dataSource={this.state.dataSource}
        renderRow={this.renderApartments.bind(this)}
        style={styles.listView}/>


    <Button
        containerStyle={{padding:8, height:50, overflow:'hidden', borderRadius:6, backgroundColor: '#3D5AFE'}}
        style={{fontSize: 20, color: 'white'}}
        styleDisabled={{color: '#D50000'}}
        onPress={() => this._emailBtn()}>
        Send email
        </Button>

</View>

)
}
}


//edit=>
class EditDetails extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            title : this.props.apartment.title ,
            address: this.props.apartment.address,
            rooms: this.props.apartment.rooms,
            }

    }


    /**
     * when pressing the Save button
     */
    _handlePress() {

        this.props.apartment.title = this.state.title;
        this.props.apartment.address = this.state.address;
        this.props.apartment.rooms = this.state.rooms;
          this.props.navigator.pop();
    }

    render(){
        return(
            <View style={{backgroundColor: 'white'}}>
    <Text style={styles.header}>Edit</Text>

        <TextInput
        style= {styles.input}
        onChangeText={(text) => this.setState({title : text})}
        placeholder="Title"
        value = {this.state.title}
    />
    <TextInput
        style={styles.input}
        onChangeText={(text) => this.setState({address : text})}
        placeholder="Address"
        value = {this.state.address}
    />
    <TextInput
        style={styles.input}
        onChangeText={(text) => this.setState({rooms : text})}
        placeholder="Rooms number"
        value = {this.state.rooms}
    />


    <Button
        containerStyle={{padding:10, height:45, overflow:'hidden', borderRadius:4, backgroundColor: 'lightgrey', marginBottom: 4}}
        style={{fontSize: 20, color: 'black'}}
        styleDisabled={{color: 'red'}}
        onPress={ () => this._handlePress() }>
        Save and return
    </Button>
        </View>
    )
    }

}


var App = React.createClass({

    renderScene(route, navigator) {
        if(route.name == 'AwesomeProject') {
            return <AwesomeProject navigator={navigator} {...route.passProps}  />
        }
        if(route.name == 'EditDetails') {
            return <EditDetails navigator={navigator} {...route.passProps}  />
        }
    },

    render() {
        return (
            <Navigator
        style={{ flex:1 }}
        initialRoute={{ name: 'AwesomeProject' }}
        renderScene={ this.renderScene } />
    )
    }
});


var SCREEN_WIDTH = require('Dimensions').get('window').width;

var BaseConfig = Navigator.SceneConfigs.FloatFromRight;

var CustomLeftToRightGesture = Object.assign({}, BaseConfig.gestures.pop, {
    snapVelocity: 8,
    edgeHitWidth: SCREEN_WIDTH,
});

var CustomSceneConfig = Object.assign({}, BaseConfig, {
    springTension: 100,
    springFriction: 1,
        gestures: {
        pop: CustomLeftToRightGesture,
    }
});

const styles = StyleSheet.create({

    input: {
        backgroundColor: 'white',
        height: 40,
        borderColor: 'white',
        borderWidth: 1,
        margin: 3,
    },
    listView: {
        width: 320,
        paddingTop: 1,
        backgroundColor: '#F5FCFF',
    },
    header: {
        fontWeight: 'bold',
        fontSize: 30,
        textAlign : 'center',
        color: 'black'

    },
    holder: {
        flex: 0.25,
        justifyContent: 'center',
    },
    text: {
        fontSize: 50,
        backgroundColor: 'red'
    },
    viewDetails: {
        margin: 9
    }

});


AppRegistry.registerComponent('AwesomeProject', () => App);
