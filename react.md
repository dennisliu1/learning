# Table of Contents
## Overview
## Create

<script src="https://unpkg.com/react@16.2.0/umd/react.development.js"></script>
<script src="https://unpkg.com/react-dom@16.2.0/umd/react-dom.development.js"></script>

# --- Main Concepts ---

# Creating JSX

const element = React.createElement(
    'div', // container element
    {
        // props
    }
)

- use {} to evaluate JS expressions in JSX.
- "{}" does not evaluate JS expressions!
- {} is always transformed into a string, so this helps prevent cross-site-scripting attacks.


## Example Element
### React Element with createElement
const element = React.createElement(
    'div', // container element
    {className: 'container', children: ["hello world!"]}
)

### React Element with Props
const props = {
    className: 'container',
    children: 'Hello World!'
}
const element = <div {...props}></div>

### React Element with Attributes and functions
function createHelloWorld(className) {
    return <div class={className}>
        Hello World!
    </div>
}
const element = createHelloWorld('container');

### Javascript
const element = document.createElement('div')
element.className = 'container'
element.textContent = 'Hello World!'
rootElement.appendChild(element)

### HTML Result
<div id="root">
<div class="container">
    Hello World!
</div>
</div>


# Rendering React Elements

ReactDOM.render(element, rootElement);

- React Elements are immutable.
- to update a react element, create a new element and run ReactDOM.render() again.
- always start React Components with a capital letter (lower case == native DOM!)
- props are read-only == declare pure functions!

# Function and Class Components and Props

## Creating Components

### method 1 - basic Javascript function
function Welcome(props) {
    return <div>{props.name}</div>
}

### method 2 - ES6 class
class Welcome extends React.Component {
    render() {
        return <div>{this.props.name}</div>
    }
}

## Using Components
const element = <Welcome name="blah" />

# State and Lifecycle
- use a ES6 class so you have access to functions

## ES6 class

class Clock extends React.Component {
    constructor(props) {
        super(props);
        this.state = {date: new Date();}
    }

    componentDidMount() {
        this.timerID = setInterval(
            function() {
                this.tick()
            },
            1000
        );
    }

    componentWillUnmount() {
        clearInterval(this.timerID);
    }

    tick() {
        this.setState({
            date: new Date()
        });
    }

    render() {
        return (
            <div>
                <h1>Hello world!</h1>
                <h2>It is {this.props.date.toLocaleTimeString()}.</h2>
            </div>
        );
    }
}

ReactDOM.render(
    <Clock />,
    rootElement
)

## states
- use this.setState(props) to update state
- props = {
    key: new_value
}
- props only modifies the passed in values, existing ones are untouched!
- setState is async. incrementing should use: this.setState(function(state, props) {
    key: state.key + props.key
}) with for example props = {key: 10}.
- you can use states of a component in lower components, doing top-down data flow.

# Events
- similar to DOM element events
    - React Events use camelCase (instead of lowercase)
    - JSX passes functions to event handler instead of a string
    - cannot pass false to prevent default behaviour in react. Use e.preventDefault() in the function.
        - e = synthetic event (the react event)
        function ActionLink() {
            function handleClick(e) {
                e.preventDefault();
                console.log('The link was clicked.');
            }

            return (
                <a href="#" onClick={handleClick}>
                Click me
                </a>
            );
        }
    - if using an ES6 class, define the function as a method of the class.
    - to ensure "this" keyword is set correctly, use:
        - public class fields syntax
            - functionName = () => {
                ...
            }
        - arrow function
            - render() {
                return (
                    <div onClick={(e) => ... }>
                    </div>
                )
            }
            - not efficient because every callback from this component will do extra re-rendering. Do this in the constructor instead.
            - have to be explicit about passing the e argument.
        - bind
            - render() {
                return (
                    <div onClick={this.functionName.bind(this, ...)}>
                    </div>
                )
            }
            - the e argument is automatically forwarded using this syntax.

## example

### HTML

<button onclick="activateLasers()">
  Activate Lasers
</button>

### React

<button onClick={activateLasers}>
  Activate Lasers
</button>

# Conditional Rendering

## 1: use JS if statements (or the conditional operator () ? t : f )

function Hello(props) {
    const isEnglish = props.isEnglish;
    if (isEnglish) {
        return <Hello />;
    }
    else {
        return <Bonjour />;
    }
}

function Hello(props) {
    const isEnglish = props.isEnglish;
    const result = (isEnglish) ? <Hello /> : <Bonjour />;
    return result;
}

ReactDOM.render(
    <Hello isEnglish={false} />,
    rootElement
);

## 2: element variables
- store element as a variable

class HelloControl extends React.Component {
    render() {
        const isEnglish = this.state.isEnglish;
        let button;

        if (isEnglish) {
            button = <Hello onClick={this.handleEnglishHello} />;
        }
        else {
            button = <Bonjour onClick={this.handleFrnechHello} />;
        }

        return (
            <div>
                {button}
            </div>
        )
    }
}

## 3: inline if using &&
- useful for inserting conditional elements

function Hello(props) {
    const isEnglish = props.isEnglish;
    return(<div>
        {isEnglish && 
            <Hello />
        }
    </div>
    );
}

## 4: don't render component using null
- stops render() but still goes through rest of component lifecycle methods (componentDidUpdate, componentDidMount, etc.)

function Hello(props) {
    const language = props.language;
    if (language === "english") {
        return <Hello />;
    }
    else if(language === "french") {
        return <Bonjour />;
    }
    else {
        return null;
    }
}

# List and Keys
- since you can store components as variables, you can also generate them using loops and maps:

const numbers = [1, 2, 3, 4, 5];
const listItems = numbers.map((number) => 
    <li>{number}</li>
);


## keys
- use a key attribute for each element in the list so each element generated is identifiable by react:
- keys should be unique in the list's scope!
- keys are not global.

const listItems = numbers.map((number) =>
    <li key={number.toString()}>
        {number}
    </li>
);

## if you have no stable key, use the id (only when order of items does not change)
const listItems = numbers.map((number, id) =>
    <li key={id}>
        {number}
    </li>
);

## use keys for each element in the list/array/parent!
- bad:
<ul>
    <ListItem>
        <li key={key}></li>
    </ListItem>
</ul>

- good:
<ul>
    <ListItem key={key}>
        <li></li>
    </ListItem>
</ul>

## you can embed the map inside the JSX:

function NumberList(props) {
    return (
        <ul>
        {numbers.map((number) =>
            <li key={number.toString()}>
                {number}
            </li>
        )}
        </ul>
    )
}

# Forms (Controlled Components)
- to handle forms in react, use Controlled Components

## example (input, textarea, select)
- for an input in the form, save its value via onChange and render it using value

class MyForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        this.setState({value: e.target.value});
    }

    handleSubmit(e) {
        console.log("a name was submitted: "+ this.state.value);
        e.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Name:
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Submit a Name" />
            </form>
        )
    }
}

## textarea
- same as input

constructor(props) {
    this.state = {
        ...
        value: "text"
    }
}

handleChange(event) {
    this.setState({value: event.target.value});
}

render() {
    return (
        ...
        <textarea value={this.state.value} onChange={this.handleChange} />
    )
}

## select
- same as input, except the value matches an option

class MyForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: 'coconut',
            valueMulti: ['coconut', 'mango']
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        this.setState({value: e.target.value});
    }

    handleChangeMultiple(e) {
        this.setState({valueMulti: e.target.value});
    }

    handleSubmit(e) {
        console.log("a name was submitted: "+ this.state.value);
        e.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Pick your favourite flavour:
                    <select value={this.state.value} onChange={this.handleChange}>
                        <option value="grapefruit">Grapefruit</option>
                        <option value="lime">Lime</option>
                        <option value="coconut">Coconut</option>
                        <option value="mango">Mango</option>
                    </select>
                    <select multiple={true} value={this.state.valueMulti} onChange={this.handleChangeMultiple}>
                        <option value="grapefruit">Grapefruit</option>
                        <option value="lime">Lime</option>
                        <option value="coconut">Coconut</option>
                        <option value="mango">Mango</option>
                    </select>
                </label>
                <input type="submit" value="Submit a Name" />
            </form>
        )
    }
}

## file input
- file inputs are read-only, so you must use an uncontrolled component.

## checkbox
- use value = e.target.checked

## multiple inputs
class MyForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: 'coconut',
            valueMulti: ['coconut', 'mango']
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const target = e.target;
        const name = target.name;
        const value = target.value;
        this.setState({[name]: value});
        /* 
        same as:
        var partialState = {};
        partialState[name] = value;
        this.setState(partialState);
        */
    }

    handleSubmit(e) {
        console.log("a name was submitted: "+ this.state.value);
        e.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Pick your favourite flavour:
                    <select name="value" value={this.state.value} onChange={this.handleChange}>
                        <option value="grapefruit">Grapefruit</option>
                        <option value="lime">Lime</option>
                        <option value="coconut">Coconut</option>
                        <option value="mango">Mango</option>
                    </select>
                    <select name="valueMulti" multiple={true} value={this.state.valueMulti} onChange={this.handleChangeMultiple}>
                        <option value="grapefruit">Grapefruit</option>
                        <option value="lime">Lime</option>
                        <option value="coconut">Coconut</option>
                        <option value="mango">Mango</option>
                    </select>
                </label>
                <input type="submit" value="Submit a Name" />
            </form>
        )
    }
}

## locking the input from user entry
- <input value={myValue} /> === when you set a value on the input the user cannot change it

# Lifting State up
- scenario: syncing multiple components
- since data is top-down data flow, have your data controlled in a parent and flowed downstream
- pre-process the values to sync so the render() will so correctly.

class MyForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value1: 'value1',
            value2: 'value2'
        }
    }

    handleValueChange() {
        const target = e.target;
        const name = target.name;
        const value = target.value;
        this.setState({[name]: value});
    }

    render() {
        const value1 = this.state.value1;
        const value2 = this.state.value2;

        // do logic with value1 and value2 to sync

        return (
            <div>
                <input type="text" value={value1} onChange={this.handleValueChange} />
                <input type="text" value={value2} onChange={this.handleValueChange} />
            </div>
        );
    }
}

# Composition vs Inheritance
- containment: components that don't know their children ahead of time
    - example: sidebar, dialog boxes, "generic" boxes and layouts
    - do passthrough of children into the body.
        <div>
        {props.children} 
        </div>
    - pass react components as props.
        <div>
            {props.left}
            <br />
            {props.right}
        </div>
        ...
        <Class left={<div>left</div>} right={<div>right</div>}>
        - since components are objects, you can pass them as props.
- specialization: pseudo-inheritance === generic -> specific components
    - generic = take in generic format
    - specialized = hardcode what the component would return
    - this is as close to inheritance as possible; you don't need anything more.
- don't use inheritance. You can do everything with compositions and down-stream component construction.

# thinking in react
## break UI down into a component hierarchy
## build static version in react
## identify vertical slice representation of UI state
## identify where your data should live
## add inverse data flow

# --- Advanced Guides ---
# Accessibility
# Code-splitting
# context (global props)
# error boundaries
# forwarding refs *
# Fragments *
# Higher-Order Components (HOC) *
# Integrating with other libraries *
# JSX in depth *
# Optimizing Performance *
# Portals *
# React without ES6 *
# React without JSX *
# Reconciliation (how React syncs the virtual DOM & HTML DOM) *
# Refs and the DOM *
# Render Props (functions passed as props) *
# Static Type Checking
# Strict Mode
# Typechecking with PropTypes *
# Uncontrolled Components *
# Web Components *
https://developer.mozilla.org/en-US/docs/Web/Web_Components





# --- (web)Hooks ---
# --- from Egghead.io/intro to react ---
# PropTypes
# using Partial Props and ...rest
# integrating with non-react HTML DOM
# requests

# other
https://medium.com/@robinpokorny/index-as-a-key-is-an-anti-pattern-e0349aece318
https://github.com/axios/axios
https://www.robinwieruch.de/react-pass-props-to-component/
https://www.robinwieruch.de/essential-react-libraries-framework/



# Testing
Mocha = test runner
Chai = assertion
Sinon = spies, stubs & mocks
Enzyme = Testing React component output (component tests)
Jest = Rendered DOM snapshot based testing (snapshot tests)
