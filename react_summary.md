# Table of Contents
# Overview
# Hello World
# Basics - Creating and Rendering
# Components and Props - Function and Class
# State and Lifecycle of a Component
# Events
# Conditional Rendering
# List and Keys
# Forms (Controlled Components)
# Lifting State up (syncing components)
# Composition vs Inheritance





# Overview
# Main Concepts
- React is a Javascript library for building user interfaces.
- It uses HTML declared in javascript (JSX) to build HTML elements.
- React handles the rendering of the JSX code via a shadow DOM.
- React treats HTML elements as components, letting you create your own custom elements.
- React can sit on the front-end and back-end, depending on how it is used.
- simple data flow to make states and data syncing easy to setup.

# Definitions
- JSX = new template language for React to declare elements.
- element = React element = description of the HTML DOM element but in React.
- component = JS functions that adds logic and abstraction to how elements are presented.
- prop = 
- state = 
- event = 
- render = 


# JSX
- template language for React elements.
- {...} = embedding JS expressions (expression always escaped into string; prevents XSS attacks!)
- {{...}} = embedding raw "innerHTML" expressions. (DANGEROUS!)
- single line: return <div></div>
- multi line: return (
    <div></div>
)
- you can end element tags to shorten syntax: return <div /> == <div></div>
- JSX is actually shorthand for doing React.createElement() calls.


# Basics - Creating and Rendering

## Element
React.createElement()
- React HTML element. 
- immutable == created elements cannot be changed.

### result
<div>name</div>

### method 1 - JSX
const element = (
    <div>name</div>
);

### method 2 - React.createElement()
const element = React.createElement(
    'div',
    'name'
);

### method 3 - basic Javascript function
function Welcome(props) {
    return <div>{props.name}</div>
}

### method 4 - ES6 class
class Welcome extends React.Component {
    render() {
        return <div>{this.props.name}</div>
    }
}

const element = <Welcome name="blah" />

## rendering elements
RenderDOM.render(element, rootElement);
- RenderDOM.render handles all rendering of React Components to the HTML DOM.
- run RenderDOM.render to update the element in the HTML DOM.
- updates occuring by states and props of the element don't need RenderDOM.render.
- RenderDOM.render runs a diff against the DOM and changes only what is needed.

# State and Lifecycle of a Component
# Events
# Conditional Rendering
# List and Keys
# Forms (Controlled Components)
# Lifting State up (syncing components)
# Composition vs Inheritance
